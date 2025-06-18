package com.andef.itcourses

import android.app.Application
import com.andef.itcourses.di.DaggerAppComponent

class ITCoursesApp : Application() {
    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }
}