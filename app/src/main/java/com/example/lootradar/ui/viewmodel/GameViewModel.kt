package com.example.lootradar.ui.viewmodel

import androidx.compose.material3.Text
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lootradar.data.local.FilterManager
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
    private val refreshGamesUseCase: RefreshGamesUseCase,
    private val filterManager: FilterManager): ViewModel() {
    private val _uiState = MutableStateFlow<GameUiState>(GameUiState.Loading)
    val uiState: StateFlow<GameUiState> = _uiState

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    init {
        viewModelScope.launch {
           getGamesUseCase().collect{ list ->
                _uiState.value = GameUiState.Success(list)
            }
        }
        fetchDeals()
    }

    fun fetchDeals(isSwipeToRefresh: Boolean = false){
        viewModelScope.launch {
            if (isSwipeToRefresh){
                _isRefreshing.value = true
            }else{
                _uiState.value = GameUiState.Loading
            }
            try {
                refreshGamesUseCase()
            }catch (e: Exception){
                if (!isSwipeToRefresh) {
                    _uiState.value = GameUiState.Error(e.message ?: "Bilinmeyen Hata")
                }
            } finally {
                _isRefreshing.value = false
            }
        }
    }

    fun saveFilter(platform: String){
        viewModelScope.launch {
            filterManager.savePlatform(platform)
        }
    }
}