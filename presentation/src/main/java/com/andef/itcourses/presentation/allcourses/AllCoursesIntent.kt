package com.andef.itcourses.presentation.allcourses

import com.andef.itcourses.domain.entities.Course

sealed class AllCoursesIntent {
    data object LoadCourses : AllCoursesIntent()
    data object SortByDate : AllCoursesIntent()
    data class AddFavoriteCourse(val course: Course) : AllCoursesIntent()
    data class RemoveFavoriteCourse(val courseId: Int) : AllCoursesIntent()
}