package com.andef.itcourses.domain.entities

import kotlinx.datetime.LocalDate

data class Course(
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: Float,
    val startDate: LocalDate,
    val hasLike: Boolean,
    val publishDate: LocalDate
)