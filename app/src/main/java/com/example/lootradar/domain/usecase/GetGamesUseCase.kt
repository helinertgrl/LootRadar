package com.example.lootradar.domain.usecase

import com.example.lootradar.data.local.FilterManager
import com.example.lootradar.data.local.GameEntity
import com.example.lootradar.data.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val repository: GameRepository,
    private val filterManager: FilterManager
) {
    operator fun invoke(): Flow<List<GameEntity>> {
       return repository.getAllGamesLive().combine(filterManager.getSavedPlatform()) { games, selectedPlatform ->
           if (selectedPlatform == "Hepsi") {
               games
           } else{
               games.filter { it.platform == selectedPlatform }
           }
       }
    }
}