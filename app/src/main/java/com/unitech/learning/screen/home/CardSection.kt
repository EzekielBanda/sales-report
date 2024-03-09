package com.unitech.learning.screen.home

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.unitech.learning.Card
import com.unitech.learning.ui.theme.PurpleEnd
import com.unitech.learning.ui.theme.PurpleStart

val cards = listOf(
    Card(
        cardType = "Shoes",
        quantity = 5,
        cardName = "Shoe Sales",
        totalPrice = 56.89,
        color = getGradient(PurpleStart, PurpleEnd)
    )
)

fun getGradient(
    startColor: Color,
    endColor: Color,
): Brush{
    return Brush.horizontalGradient(
        colors = listOf(startColor, endColor)
    )
}

@Composable
fun CardSection() {
   LazyRow {
       items(cards.size) { index ->
           CardItem(index)
       }
   }
}

@Composable
fun CardItem(
    index: Int
) {
    val card = cards[index]
    var lastItemPaddingEnd = 0.dp
    if (index == cards.size - 1) {
        lastItemPaddingEnd = 16.dp
    }
    var imageVector = ImageVector
}