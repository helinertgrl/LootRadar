package com.example.lootradar.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lootradar.ui.viewmodel.GameUiState
import com.example.lootradar.ui.viewmodel.GameViewModel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun LootScreen(viewModel: GameViewModel){

    val state by viewModel.uiState.collectAsState()
    val platforms = listOf("Hepsi","PC","Steam", "Epic","PlayStation", "Xbox")

    var isRefreshing by remember { mutableStateOf(false) }


    Column(modifier = Modifier.fillMaxSize()) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) { items(platforms) { platform ->

            FilterChip(
                selected = true,
                onClick = { viewModel.saveFilter(platform)},
                label = {Text(platform)}
            )
        }
        }

        Box(
            modifier = Modifier.fillMaxSize()
                .weight(1f),
            contentAlignment = Alignment.Center) {

            when(val currentState = state){
                is GameUiState.Loading -> {CircularProgressIndicator()}
                is GameUiState.Error -> {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(currentState.message)
                        Button(onClick = {viewModel.fetchDeals()}) { Text("Tekrar Dene")}
                    }

                }
                is GameUiState.Success -> {

                    LaunchedEffect(currentState.games) {
                        isRefreshing = false
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        PullToRefreshBox(
                            isRefreshing = isRefreshing,
                            onRefresh = {
                                isRefreshing = true
                                viewModel.fetchDeals(isSwipeToRefresh = true)
                            }
                        ) {
                            LazyColumn {
                                items(currentState.games){ game ->
                                    Card(
                                        modifier = Modifier.fillMaxWidth()
                                            .padding(8.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                                    ) {
                                        Row (modifier = Modifier.padding(12.dp),
                                            verticalAlignment = Alignment.CenterVertically){
                                            AsyncImage(
                                                model = game.thumbnail,
                                                contentDescription = "Kapak Resmi",
                                                modifier = Modifier.size(120.dp,68.dp)
                                                    .clip(RoundedCornerShape(8.dp)),
                                                contentScale = ContentScale.Crop
                                            )
                                            Column( modifier = Modifier.padding(start = 8.dp)) {
                                                Text(game.title,
                                                    fontWeight = FontWeight.Bold,
                                                    style = MaterialTheme.typography.titleMedium)
                                                Text(game.platform,
                                                    style = MaterialTheme.typography.bodySmall,
                                                    color = Color.Gray
                                                )
                                                Text(game.discountInfo,
                                                    style = MaterialTheme.typography.labelLarge,
                                                    color = Color(0xFF388E3C))
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

