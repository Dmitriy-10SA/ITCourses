package com.andef.itcourses.domain.usecases

import com.andef.itcourses.domain.repository.CourseRepository
import javax.inject.Inject

class FavoriteCoursesUseCase @Inject constructor(private val repository: CourseRepository) {
    operator fun invoke() = repository.favoriteCourses()
}