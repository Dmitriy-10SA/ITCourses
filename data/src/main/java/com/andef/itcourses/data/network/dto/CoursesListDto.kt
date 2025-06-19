package com.andef.itcourses.data.network.dto

import com.google.gson.annotations.SerializedName

data class CoursesListDto(
    @SerializedName("courses")
    val courses: List<CourseDto>
)
