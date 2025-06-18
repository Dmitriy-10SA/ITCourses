package com.andef.itcourses.domain.repository

import com.andef.itcourses.domain.entities.Course

interface CourseRepository {
    suspend fun loadCourses(): List<Course>
}