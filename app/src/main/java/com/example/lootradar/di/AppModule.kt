package com.example.lootradar.di

import android.content.Context
import com.example.lootradar.data.local.FilterManager
import com.example.lootradar.data.local.GameDao
import com.example.lootradar.data.local.LootDatabase
import com.example.lootradar.data.remote.GameApi
import com.example.lootradar.data.remote.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGameApi(): GameApi{
        return RetrofitClient.api
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): LootDatabase {
        return LootDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideGameDao(database: LootDatabase): GameDao {
        return database.gamedao()
    }

    @Provides
    @Singleton
    fun provideFilterManager(@ApplicationContext context: Context): FilterManager{
        return FilterManager(context)
    }
}