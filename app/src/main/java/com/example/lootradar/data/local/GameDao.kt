package com.example.lootradar.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.lootradar.data.local.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertGames(games: List<GameEntity>)

    @Query("SELECT * FROM GAMES_TABLE")
    fun getAllGames(): Flow<List<GameEntity>>

    @Update
    suspend fun updateGame(game: GameEntity)
}