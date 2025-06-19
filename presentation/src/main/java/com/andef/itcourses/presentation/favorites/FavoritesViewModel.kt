package com.andef.itcourses.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.itcourses.domain.usecases.AddFavoriteCourseUseCase
import com.andef.itcourses.domain.usecases.FavoriteCoursesUseCase
import com.andef.itcourses.domain.usecases.RemoveFavoriteCourseUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritesViewModel @Inject constructor(
    favoriteCoursesUseCase: FavoriteCoursesUseCase,
    private val addFavoriteCourseUseCase: AddFavoriteCourseUseCase,
    private val removeFavoriteCourseUseCase: RemoveFavoriteCourseUseCase
) : ViewModel() {
    val favoriteCourses = favoriteCoursesUseCase.invoke()

    fun send(intent: FavoritesIntent) {
        when (intent) {
            is FavoritesIntent.RemoveFavoriteCourse -> {
                removeFavoriteCourse(intent.courseId)
            }
        }
    }

    private fun removeFavoriteCourse(courseId: Int) {
        viewModelScope.launch {
            try {
                removeFavoriteCourseUseCase.invoke(courseId = courseId)
            } catch (_: Exception) {
            }
        }
    }
}