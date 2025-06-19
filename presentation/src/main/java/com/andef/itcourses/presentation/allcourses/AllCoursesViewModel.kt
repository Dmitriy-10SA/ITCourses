package com.andef.itcourses.presentation.allcourses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.itcourses.domain.entities.Course
import com.andef.itcourses.domain.usecases.AddFavoriteCourseUseCase
import com.andef.itcourses.domain.usecases.FavoriteCoursesUseCase
import com.andef.itcourses.domain.usecases.LoadCoursesUseCase
import com.andef.itcourses.domain.usecases.RemoveFavoriteCourseUseCase
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllCoursesViewModel @Inject constructor(
    private val loadCoursesUseCase: LoadCoursesUseCase,
    favoriteCoursesUseCase: FavoriteCoursesUseCase,
    private val addFavoriteCourseUseCase: AddFavoriteCourseUseCase,
    private val removeFavoriteCourseUseCase: RemoveFavoriteCourseUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(AllCoursesState())
    val state: StateFlow<AllCoursesState> = _state

    val favoriteCourses = favoriteCoursesUseCase.invoke()

    fun send(intent: AllCoursesIntent) {
        when (intent) {
            AllCoursesIntent.LoadCourses -> {
                loadCourses()
            }

            AllCoursesIntent.SortByDate -> {
                sortByDate()
            }

            is AllCoursesIntent.AddFavoriteCourse -> {
                addFavoriteCourse(course = intent.course)
            }

            is AllCoursesIntent.RemoveFavoriteCourse -> {
                removeFavoriteCourse(courseId = intent.courseId)
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

    private fun sortByDate() {
        _state.value = _state.value.copy(isSortByDate = !_state.value.isSortByDate)
    }

    private fun loadCourses() {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(isLoading = true, isError = false)
                val allCourses = loadCoursesUseCase.invoke()
                mutableListOf<Deferred<Unit>>().apply {
                    allCourses.forEach { course ->
                        if (course.hasLike) add(async { addFavoriteCourseUseCase.invoke(course) })
                    }
                }.awaitAll()
                _state.value = _state.value.copy(
                    allCourses = allCourses,
                    sortByDateAllCourses = allCourses.sortedBy { it.publishDate }
                )
            } catch (_: Exception) {
                _state.value = _state.value.copy(isError = true)
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }
}