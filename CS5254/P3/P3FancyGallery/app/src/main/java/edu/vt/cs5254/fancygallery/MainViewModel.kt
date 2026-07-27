package edu.vt.cs5254.fancygallery

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.vt.cs5254.fancygallery.api.GalleryItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * CS 5254 Project 3 - FancyGallery
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.04.25
 */

// 20.28  Shiny new ViewModel
private const val TAG = "GalleryViewModel"

class MainViewModel : ViewModel() {
    private val photoRepository = PhotoRepository()
    private val _galleryItems: MutableStateFlow<List<GalleryItem>> = MutableStateFlow(emptyList())
    val galleryItems: StateFlow<List<GalleryItem>>
        get() = _galleryItems.asStateFlow()

    fun reloadGalleryItems() {
        _galleryItems.value = emptyList()
        loadImages()
    }

    private fun loadImages() {
        viewModelScope.launch {
            try {
                val items = photoRepository.fetchPhotos(99)
                Log.d(TAG, "Items received: $items")
                _galleryItems.value = items
            } catch (ex: Exception) {
                Log.e(TAG, "Failed to fetch gallery items", ex)
            }
        }
    }

    init {
        loadImages()
    }
}