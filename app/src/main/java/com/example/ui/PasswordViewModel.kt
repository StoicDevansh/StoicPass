package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.data.PasswordEntry
import com.example.data.PasswordRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PasswordViewModel(private val repository: PasswordRepository) : ViewModel() {

    val uiState: StateFlow<List<PasswordEntry>> = repository.allPasswords
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun insert(password: PasswordEntry) {
        viewModelScope.launch {
            repository.insert(password)
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch {
            repository.deleteById(id)
        }
    }

    suspend fun getPasswordById(id: Int): PasswordEntry? {
        return repository.getPasswordById(id)
    }
}

class PasswordViewModelFactory(private val repository: PasswordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PasswordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PasswordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
