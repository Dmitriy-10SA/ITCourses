package com.andef.itcourses.data.mapper

import com.andef.itcourses.data.db.dbmodel.CourseDbModel
import com.andef.itcourses.data.network.dto.CourseDto
import com.andef.itcourses.data.network.dto.CoursesListDto
import com.andef.itcourses.domain.entities.Course
import kotlinx.datetime.LocalDate
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toKotlinLocalDate
import javax.inject.Inject

class CourseMapper @Inject constructor() {
    private fun map(courseDto: CourseDto): Course {
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

    fun map(coursesListDto: CoursesListDto): List<Course> {
        return coursesListDto.courses.map {
            this.map(it)
        }
    }

    fun map(course: Course): CourseDbModel {
        return CourseDbModel(
            id = course.id,
            title = course.title,
            text = course.text,
            price = course.price,
            rate = course.rate,
            startDate = course.startDate.toInt(),
            hasLike = course.hasLike,
            publishDate = course.publishDate.toInt()
        )
    }

    fun map(courseDbModel: CourseDbModel): Course {
        return Course(
            id = courseDbModel.id,
            title = courseDbModel.title,
            text = courseDbModel.text,
            price = courseDbModel.price,
            rate = courseDbModel.rate,
            startDate = courseDbModel.startDate.toLocalDate(),
            hasLike = courseDbModel.hasLike,
            publishDate = courseDbModel.publishDate.toLocalDate()
        )
    }

    private fun LocalDate.toInt(): Int {
        val date = this.toJavaLocalDate()
        return date.year * 10000 + date.monthValue * 100 + date.dayOfMonth
    }

    private fun Int.toLocalDate(): LocalDate {
        val year = this / 10000
        val month = (this % 10000) / 100
        val day = this % 100
        return java.time.LocalDate.of(year, month, day).toKotlinLocalDate()
    }
}