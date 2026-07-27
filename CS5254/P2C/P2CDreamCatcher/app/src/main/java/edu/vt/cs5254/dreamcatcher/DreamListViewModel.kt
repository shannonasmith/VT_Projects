package edu.vt.cs5254.dreamcatcher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * CS 5254 Project 2C - Dreamcatcher
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.04.09
 */

@DelicateCoroutinesApi
class DreamListViewModel : ViewModel() {
    private val dreamRepository = DreamRepository.get()

    // 12.26 val dreams = dreamRepository.getDreams() --> 12.28 removed
    // 12.28
    private val _dreams: MutableStateFlow<List<Dream>> = MutableStateFlow(emptyList()) // like a cache
    val loadDreamsFlow: StateFlow<List<Dream>>
        get() = _dreams.asStateFlow()

    // 15.7 expose new_dream as a function...
    suspend fun addDream(dream: Dream) {
        dreamRepository.addDream(dream)
    }
    // go back to DLF, which can now access new Dream

    // expose delete_dream as a function...
    fun deleteDream(dream: Dream) {
        viewModelScope.launch { // 12.26
            DreamRepository.get().deleteDream(dream) // does not need to be suspend function
        }
    }

    init {
        viewModelScope.launch { // 12.26
            dreamRepository.getDreams().collect { // collecting the very first dream
                _dreams.value = it // 12.28
            }
        }
    }

}