package com.andef.itcourses.presentation.favorites

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.andef.itcourses.ViewModelFactory
import com.andef.itcourses.design.coursecard.ui.UiCourseCard
import com.andef.itcourses.design.text.ui.UiText
import com.andef.itcourses.design.ui.theme.White
import com.andef.itcourses.navigation.Screen

@Composable
fun FavoritesScreen(
    navHostController: NavHostController,
    viewModelFactory: ViewModelFactory,
    paddingValues: PaddingValues
) {
    val viewModel: FavoritesViewModel = viewModel(factory = viewModelFactory)
    val favoriteCourses = viewModel.favoriteCourses.collectAsState(setOf())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp)
            .padding(paddingValues)
    ) {
        Spacer(modifier = Modifier.padding(12.dp))
        UiText(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            text = "Избранное",
            fontSize = 22.sp,
            color = White
        )
        Spacer(modifier = Modifier.padding(12.dp))
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(
                items = favoriteCourses.value.toList(),
                key = { it.id }
            ) { course ->
                UiCourseCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItem(),
                    course = course,
                    onLikeClick = {
                        viewModel.send(FavoritesIntent.RemoveFavoriteCourse(course.id))
                    },
                    isFavorite = true,
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