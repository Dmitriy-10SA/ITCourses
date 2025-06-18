package com.andef.itcourses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.andef.itcourses.design.coursecard.ui.UiCourseCard
import com.andef.itcourses.design.ui.theme.Dark
import com.andef.itcourses.design.ui.theme.ITCoursesTheme
import com.andef.itcourses.domain.Course
import kotlinx.datetime.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ITCoursesTheme(darkTheme = true) {
                Scaffold(containerColor = Dark, modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        UiCourseCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            course = Course(
                                id = 1,
                                title = "title",
                                text = "text",
                                price = 999,
                                rate = 4.4f,
                                startDate = LocalDate.parse("2025-05-05"),
                                hasLike = false,
                                publishDate = LocalDate.parse("2025-05-05")
                            ),
                            onLikeClick = {},
                            onMoreDetailedClick = {}
                        )
                    }
                }
            }
        }
    }
}