package com.andef.itcourses.presentation.favorites

sealed class FavoritesIntent {
    data class RemoveFavoriteCourse(val courseId: Int) : FavoritesIntent()
}