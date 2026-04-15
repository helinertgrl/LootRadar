package com.example.lootradar

interface GameApi {
    suspend fun fetchLootGames(): List<GameEntity>
}