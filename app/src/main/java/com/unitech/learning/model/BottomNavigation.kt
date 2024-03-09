package com.unitech.learning.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigation(
    val title: String,
    @DrawableRes val iconId: Int
)
