package com.mark.mahlola.features.home.ui

import androidx.compose.ui.graphics.Brush

data class CardItem(
        val cardType:String,
        val cardNumber:String,
        val cardName:String,
        val balance:String,
        val color:Brush
)
