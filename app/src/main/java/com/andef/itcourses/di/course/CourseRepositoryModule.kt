package com.andef.itcourses.di.course

import com.andef.itcourses.data.repository.CourseRepositoryImpl
import com.andef.itcourses.domain.repository.CourseRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface CourseRepositoryModule {
    @Binds
    @Singleton
    fun bindCourseRepository(impl: CourseRepositoryImpl): CourseRepository
}