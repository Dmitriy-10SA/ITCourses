package com.andef.itcourses.domain.usecases

import com.andef.itcourses.domain.repository.CourseRepository
import javax.inject.Inject

class RemoveFavoriteCourseUseCase @Inject constructor(private val repository: CourseRepository) {
    suspend operator fun invoke(courseId: Int) {
        repository.removeFavoriteCourse(courseId = courseId)
    }
}