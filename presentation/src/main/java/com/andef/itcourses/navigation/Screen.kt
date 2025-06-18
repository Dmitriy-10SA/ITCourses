package com.andef.itcourses.navigation

import com.andef.itcourses.design.R
import com.andef.itcourses.design.bottombar.item.UiBottomBarItem

sealed class Screen(val route: String) {
    data object LoginScreen : Screen(LOGIN_SCREEN_ROUTE)

    data object MainScreens : Screen(MAIN_SCREENS_ROUTE) {
        data object CoursesScreens : Screen(COURSES_SCREENS_ROUTE) {
            data object CourseInfoScreen : Screen(COURSE_INFO_SCREENS_ROUTE)
            data object AllCoursesScreen : Screen(ALL_COURSES_SCREEN_ROUTE)
        }

        data object FavoritesScreen : Screen(FAVOURITES_SCREENS_ROUTE)
        data object AccountScreen : Screen(ACCOUNT_SCREENS_ROUTE)
    }

    companion object {
        private const val LOGIN_SCREEN_ROUTE = "login_screen_route"

        private const val MAIN_SCREENS_ROUTE = "main_screens_route"
        private const val COURSES_SCREENS_ROUTE = "courses_screens_route"
        private const val ALL_COURSES_SCREEN_ROUTE = "all_courses_screen_route"
        private const val COURSE_INFO_SCREENS_ROUTE = "course_info_screens_route"
        private const val FAVOURITES_SCREENS_ROUTE = "favourites_screens_route"
        private const val ACCOUNT_SCREENS_ROUTE = "account_screens_route"

        val mainScreensAsUiBottomBarItems = listOf(
            UiBottomBarItem(
                iconResId = R.drawable.home,
                text = "Главная",
                contentDescription = "Дом",
                route = MainScreens.CoursesScreens.route
            ),
            UiBottomBarItem(
                iconResId = R.drawable.favorite,
                text = "Избранное",
                contentDescription = "Флаг избранного",
                route = MainScreens.FavoritesScreen.route
            ),
            UiBottomBarItem(
                iconResId = R.drawable.account,
                text = "Аккаунт",
                contentDescription = "Знак с человечком",
                route = MainScreens.AccountScreen.route
            )
        )
        val mainScreensAsRoutes = listOf(
            MainScreens.CoursesScreens.route,
            MainScreens.FavoritesScreen.route,
            MainScreens.AccountScreen.route
        )
    }
}