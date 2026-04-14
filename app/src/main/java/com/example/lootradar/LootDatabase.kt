package com.example.lootradar

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GameEntity::class], version = 1, exportSchema = false)
abstract class LootDatabase(): RoomDatabase(){
    abstract fun gamedao(): GameDao

    companion object{
        @Volatile
        private var INSTANCE : LootDatabase? = null

        fun getDatabase(context: Context): LootDatabase{
            return INSTANCE ?: synchronized(lock = this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LootDatabase::class.java,
                    "loot_tracker_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}