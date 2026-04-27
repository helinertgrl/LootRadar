package com.example.lootradar.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.lootradar.ui.viewmodel.GameUiState
import com.example.lootradar.ui.viewmodel.GameViewModel

@Composable
fun LootScreen(viewModel: GameViewModel){

    val state by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        when(val currentState = state){
            is GameUiState.Loading -> {CircularProgressIndicator()}
            is GameUiState.Error -> {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(currentState.message)
                    Button(onClick = {viewModel.fetchDeals()}) {
                        Text("Tekrar Dene")
                    }
                }

            }
            is GameUiState.Success -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {viewModel.fetchDeals()}) {
                        Text("Fırsatları Yenile")
                    }
                    LazyColumn {
                        items(currentState.games){ game ->
                            Text(text = "Oyun: ${game.title} | ${game.platform} | ${game.discountInfo}")
                        }
                }
            }
            }
        }
    }
}