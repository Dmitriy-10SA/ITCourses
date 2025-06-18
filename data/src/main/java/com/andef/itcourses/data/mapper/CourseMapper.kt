package com.andef.itcourses.data.mapper

import com.andef.itcourses.data.dto.CourseDto
import com.andef.itcourses.domain.entities.Course
import kotlinx.datetime.LocalDate
import javax.inject.Inject

class CourseMapper @Inject constructor() {
    fun map(courseDto: CourseDto): Course {
        return Course(
            id = courseDto.id,
            title = courseDto.title,
            text = courseDto.text,
            price = courseDto.price,
            rate = courseDto.rate,
            startDate = LocalDate.parse(courseDto.startDate),
            hasLike = courseDto.hasLike,
            publishDate = LocalDate.parse(courseDto.publishDate)
        )
    }
}