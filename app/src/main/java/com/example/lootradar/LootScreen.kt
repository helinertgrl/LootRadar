package com.example.lootradar

import android.widget.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun LootScreen(viewModel: GameViewModel){
    val gameList by viewModel.gameloots.collectAsState()

    Column {
        Button(onClick = {viewModel.fetchDeals()}) {
            Text("Fırsatları Yenile")
        }
        LazyColumn {
            items(gameList){ game ->
                Text(text = "Oyun: ${game.title} | ${game.platform} | ${game.discountInfo}")
            }
        }
    }
}