package com.andef.itcourses.domain.repository

import com.andef.itcourses.domain.entities.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    suspend fun loadCourses(): List<Course>
    suspend fun addFavoriteCourse(course: Course)
    suspend fun removeFavoriteCourse(courseId: Int)
    fun favoriteCourses(): Flow<Set<Course>>
}