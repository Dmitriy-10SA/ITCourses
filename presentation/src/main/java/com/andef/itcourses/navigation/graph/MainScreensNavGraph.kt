package com.andef.itcourses.navigation.graph

import android.net.Uri
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.andef.itcourses.ViewModelFactory
import com.andef.itcourses.domain.entities.Course
import com.andef.itcourses.navigation.Screen
import com.andef.itcourses.presentation.allcourses.AllCoursesScreen
import com.andef.itcourses.presentation.courseinfo.CourseInfoScreen
import com.andef.itcourses.presentation.favorites.FavoritesScreen
import kotlinx.datetime.LocalDate

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
                AllCoursesScreen(
                    navHostController = navHostController,
                    paddingValues = paddingValues,
                    viewModelFactory = viewModelFactory
                )
            }
            composable(
                route = Screen.MainScreens.CoursesScreens.CourseInfoScreen.route,
                arguments = listOf(
                    navArgument(Screen.ID_PARAM) { type = NavType.IntType },
                    navArgument(Screen.TITLE_PARAM) { type = NavType.StringType },
                    navArgument(Screen.TEXT_PARAM) { type = NavType.StringType },
                    navArgument(Screen.PRICE_PARAM) { type = NavType.StringType },
                    navArgument(Screen.RATE_PARAM) { type = NavType.FloatType },
                    navArgument(Screen.START_DATE_PARAM) { type = NavType.StringType },
                    navArgument(Screen.HAS_LIKE_PARAM) { type = NavType.BoolType },
                    navArgument(Screen.PUBLISH_DATE_PARAM) { type = NavType.StringType },
                )
            ) {
                val id = it.arguments?.getInt(Screen.ID_PARAM) ?: throw IllegalArgumentException()
                val title =
                    Uri.decode(it.arguments?.getString(Screen.TITLE_PARAM))
                        ?: throw IllegalArgumentException()
                val text =
                    Uri.decode(it.arguments?.getString(Screen.TEXT_PARAM))
                        ?: throw IllegalArgumentException()
                val price =
                    Uri.decode(it.arguments?.getString(Screen.PRICE_PARAM))
                        ?: throw IllegalArgumentException()
                val rate =
                    it.arguments?.getFloat(Screen.RATE_PARAM) ?: throw IllegalArgumentException()
                val startDate = Uri.decode(it.arguments?.getString(Screen.START_DATE_PARAM))
                    ?: throw IllegalArgumentException()
                val hasLike = it.arguments?.getBoolean(Screen.HAS_LIKE_PARAM)
                    ?: throw IllegalArgumentException()
                val publishDate = Uri.decode(it.arguments?.getString(Screen.PUBLISH_DATE_PARAM))
                    ?: throw IllegalArgumentException()
                val course = Course(
                    id = id,
                    title = title,
                    text = text,
                    price = price,
                    rate = rate,
                    startDate = LocalDate.parse(startDate),
                    hasLike = hasLike,
                    publishDate = LocalDate.parse(publishDate)
                )
                CourseInfoScreen(
                    course = course,
                    navHostController = navHostController,
                    paddingValues = paddingValues,
                    viewModelFactory = viewModelFactory
                )
            }
        }
        composable(route = Screen.MainScreens.FavoritesScreen.route) {
            FavoritesScreen(
                navHostController = navHostController,
                paddingValues = paddingValues,
                viewModelFactory = viewModelFactory
            )
        }
        composable(route = Screen.MainScreens.AccountScreen.route) {

        }
    }
}