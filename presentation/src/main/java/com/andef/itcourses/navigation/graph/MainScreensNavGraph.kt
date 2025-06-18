package com.andef.itcourses.navigation.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.andef.itcourses.ViewModelFactory
import com.andef.itcourses.navigation.Screen

fun NavGraphBuilder.mainScreensNavGraph(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    viewModelFactory: ViewModelFactory
) {
    navigation(
        startDestination = Screen.MainScreens.CoursesScreens.route,
        route = Screen.MainScreens.route
    ) {
        navigation(
            startDestination = Screen.MainScreens.CoursesScreens.AllCoursesScreen.route,
            route = Screen.MainScreens.CoursesScreens.route
        ) {
            composable(route = Screen.MainScreens.CoursesScreens.AllCoursesScreen.route) {

            }
            composable(route = Screen.MainScreens.CoursesScreens.CourseInfoScreen.route) {

            }
        }
        composable(route = Screen.MainScreens.FavoritesScreen.route) {

        }
        composable(route = Screen.MainScreens.AccountScreen.route) {

        }
    }
}