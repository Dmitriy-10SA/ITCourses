package com.andef.itcourses.domain.usecases

import com.andef.itcourses.domain.entities.Course
import com.andef.itcourses.domain.repository.CourseRepository
import javax.inject.Inject

class AddFavoriteCourseUseCase @Inject constructor(private val repository: CourseRepository) {
    suspend operator fun invoke(course: Course) {
        repository.addFavoriteCourse(course = course)
    }
}