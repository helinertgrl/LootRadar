package com.example.lootradar.ui.viewmodel

import androidx.compose.material3.Text
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lootradar.data.local.GameEntity
import com.example.lootradar.data.repository.GameRepository
import com.example.lootradar.domain.usecase.GetGamesUseCase
import com.example.lootradar.domain.usecase.RefreshGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase,
    private val refreshGamesUseCase: RefreshGamesUseCase): ViewModel() {
    private val _uiState = MutableStateFlow<GameUiState>(GameUiState.Loading)
    val uiState: StateFlow<GameUiState> = _uiState

    init {
        viewModelScope.launch {
           getGamesUseCase().collect{ list ->
                _uiState.value = GameUiState.Success(list)
            }
        }
    }

    fun fetchDeals(){
        viewModelScope.launch {
            _uiState.value = GameUiState.Loading
            try {
                refreshGamesUseCase()
            }catch (e: Exception){
                _uiState.value = GameUiState.Error(e.message ?: "Bilinmeyen Hata")
            }
        }
    }
}