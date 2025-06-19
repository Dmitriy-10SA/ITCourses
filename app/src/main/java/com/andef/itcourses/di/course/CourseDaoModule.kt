package com.andef.itcourses.di.course

import android.app.Application
import com.andef.itcourses.data.db.datasource.CourseDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CourseDaoModule {
    @Singleton
    @Provides
    fun provideCourseDao(application: Application) = CourseDatabase.getInstance(application).dao
}