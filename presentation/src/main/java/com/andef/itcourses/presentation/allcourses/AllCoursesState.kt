package com.andef.itcourses.presentation.allcourses

import com.andef.itcourses.domain.entities.Course

data class AllCoursesState(
    val courses: List<Course> = listOf<Course>(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
