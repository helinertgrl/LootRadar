package com.example.lootradar.ui.viewmodel

import com.example.lootradar.data.local.GameEntity

sealed class GameUiState {
    object Loading: GameUiState()
    data class Success(val games: List<GameEntity>) : GameUiState()
    data class Error(val message: String): GameUiState()
}