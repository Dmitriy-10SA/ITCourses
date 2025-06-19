package com.andef.itcourses.presentation.allcourses

import com.andef.itcourses.domain.entities.Course

data class AllCoursesState(
    val allCourses: List<Course> = listOf<Course>(),
    val sortByDateAllCourses: List<Course> = listOf<Course>(),
    val isSortByDate: Boolean = false,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
