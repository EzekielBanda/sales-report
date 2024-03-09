package com.unitech.learning

import androidx.compose.ui.graphics.Brush

data class Card(
    val cardType: String,
    val quantity: Long,
    val cardName: String,
    val totalPrice: Double,
    val color: Brush
)
