package com.example.lootradar.domain

import androidx.compose.ui.graphics.Color
import com.example.lootradar.ui.theme.BlueRare
import com.example.lootradar.ui.theme.GreenUncommon
import com.example.lootradar.ui.theme.OrangeLegendary
import com.example.lootradar.ui.theme.PinkEpic
enum class Rarity(val badgeColor: Color) {
    Uncommon(GreenUncommon),
    Rare(BlueRare),
    Epic(PinkEpic),
    Legendary(OrangeLegendary);

    companion object {
        fun fromPrice(worth: String?): Rarity {
            val price = worth?.replace("$","")?.toDoubleOrNull() ?:0.0
            return when {
                price <= 9.99 -> Uncommon
                price <= 19.99 -> Rare
                price <= 29.99 -> Epic
                else -> Legendary
            }
        }
    }
}