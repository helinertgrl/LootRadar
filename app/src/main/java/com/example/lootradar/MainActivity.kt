package com.example.lootradar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.lootradar.ui.theme.LootRadarTheme
import androidx.lifecycle.ViewModelProvider
import com.example.lootradar.data.local.LootDatabase
import com.example.lootradar.data.remote.RetrofitClient
import com.example.lootradar.data.repository.GameRepository
import com.example.lootradar.ui.screen.LootScreen
import com.example.lootradar.ui.viewmodel.GameViewModel
import com.example.lootradar.ui.viewmodel.GameViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val dao = LootDatabase.getDatabase(applicationContext).gamedao()
        val api = RetrofitClient.api
        val repository = GameRepository(dao, api)
        val factory = GameViewModelFactory(repository)
        val myViewModel = ViewModelProvider(this, factory)[GameViewModel::class.java]

        setContent {
            LootRadarTheme {
                LootScreen(viewModel = myViewModel)
            }
        }
    }
}