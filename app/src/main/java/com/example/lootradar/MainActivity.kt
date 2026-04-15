package com.example.lootradar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.lootradar.ui.theme.LootRadarTheme
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val dao = LootDatabase.getDatabase(applicationContext).gamedao()
        val api = DummyGameApi()
        val repository = GameRepository(dao,api)
        val factory = GameViewModelFactory(repository)
        val myViewModel = ViewModelProvider(this, factory)[GameViewModel::class.java]

        setContent {
            LootRadarTheme {
                LootScreen(viewModel = myViewModel)
            }
        }
    }
}

class DummyGameApi(): GameApi{
    override suspend fun fetchLootGames(): List<GameEntity> {
        return listOf(
            GameEntity(0,"meowmagic","steam","%50 indirim",false),
            GameEntity(1,"eworlds","epic","%10 indirim",false))
    }
}