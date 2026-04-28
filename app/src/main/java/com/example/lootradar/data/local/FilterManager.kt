package com.example.lootradar.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(name = "filter_settings")

class FilterManager @Inject constructor(
    @ApplicationContext private val context: Context
){

    val PLATFORM_KEY = stringPreferencesKey("selected_platform")

    fun getSavedPlatform(): Flow<String>{
       return  context.dataStore.data.map { preferences ->
           preferences[PLATFORM_KEY] ?: "Hepsi"
       }
    }

    suspend fun savePlatform(name: String){
        context.dataStore.edit { preferences ->
            preferences[PLATFORM_KEY] = name
        }
    }
}