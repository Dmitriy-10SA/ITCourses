package com.andef.itcourses.data.repository

import com.andef.itcourses.data.db.datasource.CourseDao
import com.andef.itcourses.data.mapper.CourseMapper
import com.andef.itcourses.data.network.api.CourseApiService
import com.andef.itcourses.domain.entities.Course
import com.andef.itcourses.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CourseRepositoryImpl @Inject constructor(
    private val courseApiService: CourseApiService,
    private val courseMapper: CourseMapper,
    private val courseDao: CourseDao
) : CourseRepository {
    override suspend fun loadCourses(): List<Course> {
        val response = courseApiService.loadCourses()
        return if (response.isSuccessful && response.body() != null) {
            courseMapper.map(response.body()!!)
        } else {
            throw Exception()
        }
    }

    override suspend fun addFavoriteCourse(course: Course) {
        courseDao.addFavoriteCourse(courseMapper.map(course = course))
    }

    override suspend fun removeFavoriteCourse(courseId: Int) {
        courseDao.removeFavoriteCourse(courseId = courseId)
    }

    override fun favoriteCourses(): Flow<Set<Course>> {
        return courseDao.favoriteCourses().map { coursesList ->
            coursesList.map { courseDbModel ->
                courseMapper.map(courseDbModel = courseDbModel)
            }.toSet()
        }
    }
}