package com.example.lootradar.data.remote

import com.example.lootradar.data.local.GameEntity
import retrofit2.http.GET

interface GameApi {
    @GET("giveaways")  //endpoint
    suspend fun fetchLootGames(): List<GameEntity>
}