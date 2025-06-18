package com.andef.itcourses.presentation.allcourses

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.andef.itcourses.ViewModelFactory

@Composable
fun AllCoursesScreen(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    viewModelFactory: ViewModelFactory
) {
    val viewModel: AllCoursesViewModel = viewModel(factory = viewModelFactory)
    viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.send(AllCoursesIntent.LoadCourses)
    }
}