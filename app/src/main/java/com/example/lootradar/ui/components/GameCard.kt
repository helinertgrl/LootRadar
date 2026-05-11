package com.example.lootradar.ui.components

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.lootradar.data.local.GameEntity
import com.example.lootradar.domain.Rarity
import com.example.lootradar.ui.theme.PixelFontFamily
import com.example.lootradar.ui.theme.PixelFontFamilyBold

@Composable
fun GameCard(game: GameEntity, modifier: Modifier = Modifier) {

    val rarity = Rarity.fromPrice(game.worth)
    val slantedShape = RoundedCornerShape(
        topStart = 227.dp,
        topEnd = 45.dp,
        bottomStart = 45.dp,
        bottomEnd = 227.dp
    )
    val titleShape = RoundedCornerShape(
        topStart = 121.dp,
        topEnd = 45.dp,
        bottomStart = 45.dp,
        bottomEnd = 121.dp
    )

    Card(
        shape = RoundedCornerShape(28.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(
            width = 2.dp,
            color = rarity.badgeColor,
            shape = RoundedCornerShape(28.dp)
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFC6CED4)
        )
    ) {
        Column {
            Box(
                modifier = Modifier
                    .padding(start = 2.dp, top = 2.dp)
                .clip(slantedShape)
                .background(rarity.badgeColor)
                .border(
                    width = 1.5.dp,
                    color = Color.Black,
                    shape = slantedShape
                )
                .padding(horizontal = 16.dp, vertical = 6.dp),
                Alignment.Center
            ){
                Text(rarity.name.uppercase(),
                    fontFamily = PixelFontFamilyBold,
                    fontSize = 16.sp)
            }

            Row (
                modifier = Modifier.padding(16.dp)
            ){
                Column(
                    modifier = Modifier.height(110.dp)
                        .padding(start = 4.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text("Platform: ${game.platform}",
                        fontFamily = PixelFontFamilyBold,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("Type: ${game.type}",
                        fontFamily = PixelFontFamilyBold,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("Worth: ${game.worth}",
                        fontFamily = PixelFontFamilyBold,
                        fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("Claimed: ${game.users}",
                        fontFamily = PixelFontFamilyBold,
                        fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text("Expires: ${game.endDate}",
                        fontFamily = PixelFontFamilyBold,
                        fontSize = 14.sp)
                }

                Spacer(Modifier.weight(1f))

                AsyncImage(
                    model = game.thumbnail,
                    contentDescription = "picture",
                    modifier = Modifier
                        .size(160.dp,110.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(2.dp, color = Color.Black, RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }
            Box (
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .padding(start = 2.dp, top = 2.dp)
                    .fillMaxWidth()
                    .clip(titleShape)
                    .background(rarity.badgeColor)
                    .border(width = 2.dp, color = Color.Black, shape = titleShape)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            )
            {
                Text(game.title,
                    fontFamily = PixelFontFamilyBold,
                    fontSize = 18.sp)
            }
        }
    }
}

class GamePreviewProvider : PreviewParameterProvider<GameEntity> {
    override val values = sequenceOf(
        GameEntity(1, "Epic Game", "PC", "$45.00", 3, true, thumbnail = ""),
        GameEntity(2, "Cheap Game", "Android", "$2.00", 3, true, thumbnail = ""),
        GameEntity(3, "Free Game", "iOS", "$0.00", 3, false, thumbnail = "")
    )
}

@Preview(showBackground = true)
@Composable
fun GameCardPreview(
    @PreviewParameter(GamePreviewProvider::class) game: GameEntity
) {
    GameCard(game = game)
}