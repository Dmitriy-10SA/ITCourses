package com.andef.itcourses.design.button.type

sealed class UiButtonType {
    data class Green(val text: String, val onClick: () -> Unit) : UiButtonType()
    data class DarkGray(val text: String, val onClick: () -> Unit) : UiButtonType()
    data object VK : UiButtonType()
    data object Odnoklassniki : UiButtonType()
}