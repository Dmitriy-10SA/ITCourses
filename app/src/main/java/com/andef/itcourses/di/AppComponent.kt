package com.andef.itcourses.di

import android.app.Application
import com.andef.itcourses.MainActivity
import com.andef.itcourses.ViewModelFactory
import com.andef.itcourses.di.course.CourseApiServiceModule
import com.andef.itcourses.di.course.CourseRepositoryModule
import com.andef.itcourses.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class,
        CourseApiServiceModule::class,
        CourseRepositoryModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    val viewModelFactory: ViewModelFactory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}