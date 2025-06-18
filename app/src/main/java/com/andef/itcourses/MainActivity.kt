package com.andef.itcourses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.andef.itcourses.design.bottombar.ui.UiBottomBar
import com.andef.itcourses.design.ui.theme.Dark
import com.andef.itcourses.design.ui.theme.ITCoursesTheme
import com.andef.itcourses.design.ui.theme.White
import com.andef.itcourses.navigation.Screen
import com.andef.itcourses.navigation.graph.AppNavGraph

class MainActivity : ComponentActivity() {
    private val component by lazy {
        (application as ITCoursesApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()
            val currentDestination = navHostController.currentBackStackEntryAsState().value
            ITCoursesTheme(darkTheme = true) {
                Scaffold(
                    containerColor = Dark,
                    bottomBar = {
                        MainBottomBar(
                            currentDestination = currentDestination,
                            navHostController = navHostController
                        )
                    },
                    contentColor = White,
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    AppNavGraph(
                        navHostController = navHostController,
                        viewModelFactory = component.viewModelFactory,
                        paddingValues = innerPadding
                    )
                }
            }
        }
    }
}

@Composable
private fun MainBottomBar(
    currentDestination: NavBackStackEntry?,
    navHostController: NavHostController
) {
    var lastCoursesRoute by remember {
        mutableStateOf(Screen.MainScreens.CoursesScreens.AllCoursesScreen.route)
    }
    UiBottomBar(
        onItemClick = { item ->
            if (
                currentDestination?.destination?.hierarchy?.any {
                    it.route == item.route
                } == false
            ) {
                lastCoursesRoute = if (
                    currentDestination.destination.route ==
                    Screen.MainScreens.CoursesScreens.AllCoursesScreen.route
                ) {
                    Screen.MainScreens.CoursesScreens.AllCoursesScreen.route
                } else if (
                    currentDestination.destination.route ==
                    Screen.MainScreens.CoursesScreens.CourseInfoScreen.route
                ) {
                    Screen.MainScreens.CoursesScreens.CourseInfoScreen.route
                } else {
                    lastCoursesRoute
                }
                navHostController.navigate(item.route) {
                    popUpTo(lastCoursesRoute) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        },
        selectedItem = { item ->
            currentDestination?.destination?.hierarchy?.any {
                it.route == item.route
            } == true
        },
        items = Screen.mainScreensAsUiBottomBarItems,
        isVisible = currentDestination?.destination?.hierarchy?.any {
            it.route in Screen.mainScreensAsRoutes
        } == true
    )
}