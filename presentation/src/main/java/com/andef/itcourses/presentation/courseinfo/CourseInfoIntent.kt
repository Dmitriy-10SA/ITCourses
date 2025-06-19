package com.andef.itcourses.presentation.courseinfo

import com.andef.itcourses.domain.entities.Course

sealed class CourseInfoIntent {
    data class AddFavoriteCourse(val course: Course) : CourseInfoIntent()
    data class RemoveFavoriteCourse(val courseId: Int) : CourseInfoIntent()
}