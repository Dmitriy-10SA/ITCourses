package com.andef.itcourses.presentation.allcourses

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.andef.itcourses.ViewModelFactory
import com.andef.itcourses.design.coursecard.ui.UiCourseCard
import com.andef.itcourses.design.error.ui.UiError
import com.andef.itcourses.design.loading.ui.UiLoading
import com.andef.itcourses.design.searchbar.ui.UiSearchBar
import com.andef.itcourses.navigation.Screen

@Composable
fun AllCoursesScreen(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    viewModelFactory: ViewModelFactory
) {
    val viewModel: AllCoursesViewModel = viewModel(factory = viewModelFactory)
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) { viewModel.send(AllCoursesIntent.LoadCourses) }

    if (!state.value.isError) {
        MainContent(
            paddingValues = paddingValues,
            viewModel = viewModel,
            state = state,
            navHostController = navHostController
        )
        if (state.value.isLoading && state.value.allCourses.isEmpty()) {
            UiLoading(modifier = Modifier.commonModifier(paddingValues))
        }
    } else {
        UiError(
            modifier = Modifier.commonModifier(paddingValues),
            onRetryClick = { viewModel.send(AllCoursesIntent.LoadCourses) }
        )
    }
}

@Composable
private fun MainContent(
    paddingValues: PaddingValues,
    viewModel: AllCoursesViewModel,
    state: State<AllCoursesState>,
    navHostController: NavHostController
) {
    val favoriteCourses = viewModel.favoriteCourses.collectAsState(setOf())
    Column(modifier = Modifier.commonModifier(paddingValues)) {
        Spacer(modifier = Modifier.padding(4.dp))
        UiSearchBar(
            modifier = Modifier.fillMaxWidth(),
            placeholderText = "Search courses...",
            value = "",
            onValueChange = {},
            isWithSortByDate = true,
            onSortByDateClick = { viewModel.send(AllCoursesIntent.SortByDate) },
            onFilterButtonClick = {}
        )
        Spacer(modifier = Modifier.padding(4.dp))
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = if (state.value.isSortByDate) {
                    state.value.sortByDateAllCourses
                } else {
                    state.value.allCourses
                },
                key = { it.id }
            ) { course ->
                UiCourseCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem(),
                    course = course,
                    onLikeClick = {
                        if (favoriteCourses.value.contains(course)) {
                            viewModel.send(AllCoursesIntent.RemoveFavoriteCourse(course.id))
                        } else {
                            viewModel.send(AllCoursesIntent.AddFavoriteCourse(course))
                        }
                    },
                    isFavorite = favoriteCourses.value.contains(course),
                    onMoreDetailedClick = {
                        navHostController.navigate(
                            Screen.MainScreens.CoursesScreens.CourseInfoScreen.passCourse(
                                course = course
                            )
                        )
                    }
                )
            }
            item { Spacer(modifier = Modifier.padding(4.dp)) }
        }
    }
}

@Composable
private fun Modifier.commonModifier(paddingValues: PaddingValues) = this
    .fillMaxSize()
    .padding(horizontal = 10.dp)
    .padding(paddingValues)