package com.example.lootradar.data.repository

import com.example.lootradar.data.local.GameDao
import com.example.lootradar.data.local.GameEntity
import com.example.lootradar.data.remote.GameApi
import kotlinx.coroutines.flow.Flow

class GameRepository(private val gameDao: GameDao, private val gameapi: GameApi) {
    fun getAllGamesLive(): Flow<List<GameEntity>> {
        return gameDao.getAllGames()
    }

    suspend fun refreshGames(){
        try {
            val newgames = gameapi.fetchLootGames()
            gameDao.insertGames(newgames)
        }catch (e: Exception){
            println(e)
        }
    }
}