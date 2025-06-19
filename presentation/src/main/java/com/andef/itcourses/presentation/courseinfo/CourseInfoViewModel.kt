package com.andef.itcourses.presentation.courseinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.itcourses.domain.entities.Course
import com.andef.itcourses.domain.usecases.AddFavoriteCourseUseCase
import com.andef.itcourses.domain.usecases.FavoriteCoursesUseCase
import com.andef.itcourses.domain.usecases.RemoveFavoriteCourseUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class CourseInfoViewModel @Inject constructor(
    favoriteCoursesUseCase: FavoriteCoursesUseCase,
    private val addFavoriteCourseUseCase: AddFavoriteCourseUseCase,
    private val removeFavoriteCourseUseCase: RemoveFavoriteCourseUseCase
) : ViewModel() {
    val favoriteCourses = favoriteCoursesUseCase.invoke()

    fun send(intent: CourseInfoIntent) {
        when (intent) {
            is CourseInfoIntent.AddFavoriteCourse -> {
                addFavoriteCourse(intent.course)
            }

            is CourseInfoIntent.RemoveFavoriteCourse -> {
                removeFavoriteCourse(intent.courseId)
            }
        }
    }

    private fun addFavoriteCourse(course: Course) {
        viewModelScope.launch {
            try {
                addFavoriteCourseUseCase.invoke(course = course)
            } catch (_: Exception) {
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