package com.example.lootradar

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games: List<GameEntity>)

    @Query("SELECT * FROM GAMES_TABLE")
    fun getAllGames(): Flow<List<GameEntity>>

    @Update
    suspend fun updateGame(game: GameEntity)
}