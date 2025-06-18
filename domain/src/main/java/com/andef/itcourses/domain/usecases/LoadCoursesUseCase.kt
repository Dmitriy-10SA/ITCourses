package com.andef.itcourses.domain.usecases

import com.andef.itcourses.domain.repository.CourseRepository
import javax.inject.Inject

class LoadCoursesUseCase @Inject constructor(private val repository: CourseRepository) {
    suspend operator fun invoke() = repository.loadCourses()
}