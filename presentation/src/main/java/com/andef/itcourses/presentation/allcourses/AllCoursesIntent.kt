package com.andef.itcourses.presentation.allcourses

sealed class AllCoursesIntent {
    data object LoadCourses : AllCoursesIntent()
}