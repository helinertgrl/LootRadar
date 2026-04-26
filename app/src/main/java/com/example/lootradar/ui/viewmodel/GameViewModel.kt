package com.example.lootradar.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lootradar.data.local.GameEntity
import com.example.lootradar.data.repository.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repository: GameRepository): ViewModel() {
    private val _gameloots = MutableStateFlow<List<GameEntity>>(emptyList())
    val gameloots: StateFlow<List<GameEntity>> = _gameloots

    init {
        viewModelScope.launch {
            repository.getAllGamesLive().collect{ currentList ->
                _gameloots.value = currentList
            }
        }
    }

    fun fetchDeals(){
        viewModelScope.launch {
            repository.refreshGames()
        }
    }
}