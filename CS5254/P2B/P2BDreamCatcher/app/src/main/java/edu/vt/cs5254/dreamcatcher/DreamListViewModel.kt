package edu.vt.cs5254.dreamcatcher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * CS 5254 Project 2B - Dreamcatcher
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.03.11
 */

@DelicateCoroutinesApi
class DreamListViewModel : ViewModel() {
    private val dreamRepository = DreamRepository.get()

    // 12.26 val dreams = dreamRepository.getDreams() --> 12.28 removed
    // 12.28
    private val _dreams: MutableStateFlow<List<Dream>> = MutableStateFlow(emptyList())
    val loadDreamsFlow: StateFlow<List<Dream>>
        get() = _dreams.asStateFlow()

    init {
        viewModelScope.launch { // 12.26
            dreamRepository.getDreams().collect { // 12.28
                _dreams.value = it // 12.28
            }
        }
    }

}