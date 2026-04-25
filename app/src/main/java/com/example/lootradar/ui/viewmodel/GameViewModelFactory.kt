package com.example.lootradar.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lootradar.data.repository.GameRepository

class GameViewModelFactory(private val repository: GameRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return GameViewModel(repository) as T
    }
}