package com.example.lootradar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lootradar.ui.theme.LootRadarTheme
import androidx.lifecycle.ViewModelProvider
import com.example.lootradar.data.local.LootDatabase
import com.example.lootradar.data.remote.RetrofitClient
import com.example.lootradar.data.repository.GameRepository
import com.example.lootradar.ui.screen.LootScreen
import com.example.lootradar.ui.viewmodel.GameViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LootRadarTheme {
                val myViewModel: GameViewModel = hiltViewModel()
                LootScreen(viewModel = myViewModel)
            }
        }
    }
}