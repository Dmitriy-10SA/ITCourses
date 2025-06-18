package com.andef.itcourses.di.viewmodel

import androidx.lifecycle.ViewModel
import com.andef.itcourses.presentation.allcourses.AllCoursesViewModel
import com.andef.itcourses.presentation.login.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(impl: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AllCoursesViewModel::class)
    fun bindAllCoursesViewModel(impl: AllCoursesViewModel): ViewModel
}