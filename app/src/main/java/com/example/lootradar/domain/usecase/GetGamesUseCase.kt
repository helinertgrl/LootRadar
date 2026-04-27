package com.example.lootradar.domain.usecase

import com.example.lootradar.data.local.GameEntity
import com.example.lootradar.data.repository.GameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val repository: GameRepository
) {
    operator fun invoke(): Flow<List<GameEntity>> {
        return repository.getAllGamesLive()
    }
}