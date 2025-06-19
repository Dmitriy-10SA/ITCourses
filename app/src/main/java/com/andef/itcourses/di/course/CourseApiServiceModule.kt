package com.andef.itcourses.di.course

import com.andef.itcourses.data.ApiFactory
import com.andef.itcourses.data.network.api.CourseApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CourseApiServiceModule {
    @Provides
    @Singleton
    fun provideCourseApiService(): CourseApiService = ApiFactory.courseApiService
}