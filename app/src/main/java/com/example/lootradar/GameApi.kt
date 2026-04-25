package com.example.lootradar

import retrofit2.http.GET

interface GameApi {
    @GET("giveaways")  //endpoint
    suspend fun fetchLootGames(): List<GameEntity>
}