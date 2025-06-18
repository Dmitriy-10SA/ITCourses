package com.andef.itcourses.data.repository

import com.andef.itcourses.data.api.CourseApiService
import com.andef.itcourses.data.mapper.CourseMapper
import com.andef.itcourses.domain.entities.Course
import com.andef.itcourses.domain.repository.CourseRepository
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val courseApiService: CourseApiService,
    private val courseMapper: CourseMapper
) : CourseRepository {
    override suspend fun loadCourses(): List<Course> {
        val response = courseApiService.loadCourses()
        return if (response.isSuccessful && response.body() != null) {
            response.body()!!.map { courseMapper.map(it) }
        } else {
            throw Exception("Какие-то проблемы")
        }
    }
}