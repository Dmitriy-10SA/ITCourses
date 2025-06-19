package com.andef.itcourses.data.db.dbmodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course")
data class CourseDbModel(
    @PrimaryKey
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: Float,
    val startDate: Int,
    val hasLike: Boolean,
    val publishDate: Int
)