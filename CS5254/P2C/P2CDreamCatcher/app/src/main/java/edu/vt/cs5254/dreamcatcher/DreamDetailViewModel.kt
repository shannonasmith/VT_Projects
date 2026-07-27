package edu.vt.cs5254.dreamcatcher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*

/**
 * CS 5254 Project 2C - Dreamcatcher
 *
 * @author Shannon Smith (shae1223)
 * @version 2023.04.09
 */

@DelicateCoroutinesApi
class DreamDetailViewModel(dreamId: UUID) : ViewModel() { // 13.17
    private val dreamRepository = DreamRepository.get() // 13.16

    private val _dream: MutableStateFlow<Dream?> = MutableStateFlow(null) // 13.16
    val dream: StateFlow<Dream?> = // 13.16
        _dream.asStateFlow()

    init { // 13.17
        viewModelScope.launch {
            _dream.value = dreamRepository.getDream(dreamId)
        }
    }

    fun updateDream(onUpdate: (Dream) -> Dream) { // 13.20 AP
        _dream.update { oldDream ->
            val newDream = oldDream?.let { onUpdate(it) } ?: return
            if ((newDream == oldDream) && (newDream.entries == oldDream.entries)) {
                return
            } // 13.20
            newDream.copy(lastUpdated = Date()).apply { entries = newDream.entries }
        }
    }

    override fun onCleared() { // added 13.24
        super.onCleared()
        dream.value?.let { dreamRepository.updateDream(it) }
    }

}

@DelicateCoroutinesApi
class DreamDetailViewModelFactory( // 13.17
    private val dreamId: UUID
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return DreamDetailViewModel(dreamId) as T
    }

}
