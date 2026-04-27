package com.example.lootradar.domain.usecase

import com.example.lootradar.data.repository.GameRepository
import javax.inject.Inject

class RefreshGamesUseCase @Inject constructor(
    private val repository: GameRepository
){
    suspend operator fun invoke(){
        repository.refreshGames()
    }
}