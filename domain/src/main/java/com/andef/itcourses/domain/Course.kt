package com.andef.itcourses.domain

import kotlinx.datetime.LocalDate

data class Course(
    val id: Int,
    val title: String,
    val text: String,
    val price: Int,
    val rate: Float,
    val startDate: LocalDate,
    val hasLike: Boolean,
    val publishDate: LocalDate
)
