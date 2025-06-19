package com.andef.itcourses.navigation.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.andef.itcourses.ViewModelFactory
import com.andef.itcourses.navigation.Screen
import com.andef.itcourses.presentation.login.LoginScreen

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    viewModelFactory: ViewModelFactory,
    paddingValues: PaddingValues
) {
    NavHost(navController = navHostController, startDestination = Screen.MainScreens.route) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(
                navHostController = navHostController,
                paddingValues = paddingValues,
                viewModelFactory = viewModelFactory
            )
        }
        mainScreensNavGraph(
            navHostController = navHostController,
            paddingValues = paddingValues,
            viewModelFactory = viewModelFactory
        )
    }
}