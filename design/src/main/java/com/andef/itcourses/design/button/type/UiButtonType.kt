package com.andef.itcourses.design.button.type

sealed class UiButtonType {
    data object Green : UiButtonType()
    data object DarkGray : UiButtonType()
    data object VK : UiButtonType()
    data object Odnoklassniki : UiButtonType()
}