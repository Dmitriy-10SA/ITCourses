package com.andef.itcourses.design.bottombar.item

import androidx.compose.ui.graphics.painter.Painter

data class UiBottomBarItem(
    val icon: Painter,
    val text: String,
    val contentDescription: String,
    val route: String
)