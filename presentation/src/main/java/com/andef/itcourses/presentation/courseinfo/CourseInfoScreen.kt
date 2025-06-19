package com.andef.itcourses.presentation.courseinfo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.andef.itcourses.ViewModelFactory
import com.andef.itcourses.design.R
import com.andef.itcourses.design.button.type.UiButtonType
import com.andef.itcourses.design.button.ui.UiButton
import com.andef.itcourses.design.dateFormatter
import com.andef.itcourses.design.text.ui.UiText
import com.andef.itcourses.design.ui.theme.Dark
import com.andef.itcourses.design.ui.theme.Green
import com.andef.itcourses.design.ui.theme.Stroke
import com.andef.itcourses.design.ui.theme.White
import com.andef.itcourses.domain.entities.Course
import kotlinx.datetime.toJavaLocalDate

@Composable
fun CourseInfoScreen(
    course: Course,
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    viewModelFactory: ViewModelFactory
) {
    val viewModel: CourseInfoViewModel = viewModel(factory = viewModelFactory)

    MainContent(course = course, paddingValues = paddingValues)
    NavButtonAndLikeButton(
        course = course,
        navHostController = navHostController,
        paddingValues = paddingValues,
        viewModel = viewModel
    )
}

@Composable
private fun NavButtonAndLikeButton(
    navHostController: NavHostController,
    course: Course,
    viewModel: CourseInfoViewModel,
    paddingValues: PaddingValues
) {
    val favoriteCourses = viewModel.favoriteCourses.collectAsState(setOf())
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(
                onClick = { navHostController.popBackStack() },
                colors = iconButtonColors()
            ) {
                Icon(painter = painterResource(R.drawable.arrow_back), contentDescription = "Назад")
            }
            Spacer(Modifier.weight(1f))
            IconButton(
                onClick = {
                    if (favoriteCourses.value.contains(course)) {
                        viewModel.send(CourseInfoIntent.RemoveFavoriteCourse(course.id))
                    } else {
                        viewModel.send(CourseInfoIntent.AddFavoriteCourse(course))
                    }
                },
                colors = iconButtonColors()
            ) {
                Icon(
                    modifier = Modifier
                        .height(21.dp)
                        .width(16.dp),
                    painter = if (favoriteCourses.value.contains(course)) {
                        painterResource(R.drawable.like_flag)
                    } else {
                        painterResource(R.drawable.favorite)
                    },
                    tint = if (favoriteCourses.value.contains(course)) Green else Dark,
                    contentDescription = "Избранное"
                )
            }
        }
    }
}

@Composable
private fun iconButtonColors() = IconButtonDefaults.iconButtonColors(
    containerColor = White,
    contentColor = Dark
)

@Composable
private fun MainContent(course: Course, paddingValues: PaddingValues) {
    val image = if (course.id == 100 || course.id == 103) {
        R.drawable.course_photo1
    } else if (course.id == 101 || course.id == 104) {
        R.drawable.course_photo2
    } else {
        R.drawable.course_photo3
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding())
    ) {
        item {
            Box {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp),
                    painter = painterResource(image),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Обложка курса"
                )
                OnImageRatingAndDate(course = course)
            }
        }
        item { Spacer(Modifier.padding(10.dp)) }
        item {
            UiText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                textAlign = TextAlign.Start,
                text = course.title,
                color = White,
                fontSize = 22.sp
            )
        }
        item { Spacer(Modifier.padding(10.dp)) }
        item { AuthorInfo() }
        item { Spacer(Modifier.padding(10.dp)) }
        item { ActionButtons() }
        item { Spacer(Modifier.padding(12.dp)) }
        item { AboutCourse(course = course) }
        item { Spacer(Modifier.padding(4.dp)) }
    }
}

@Composable
private fun BoxScope.OnImageRatingAndDate(course: Course) {
    Row(
        modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .background(
                    color = Color(0xFF32333A).copy(alpha = 0.8f),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 6.dp, vertical = 1.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                tint = Green,
                painter = painterResource(R.drawable.star),
                contentDescription = "Звезда"
            )
            Text(
                text = "%.1f".format(course.rate),
                color = White,
                fontSize = 12.sp,
            )
        }
        UiText(
            modifier = Modifier
                .background(
                    color = Color(0xFF32333A).copy(alpha = 0.8f),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 6.dp, vertical = 1.dp),
            fontSize = 12.sp,
            color = White,
            text = course.startDate.toJavaLocalDate().format(dateFormatter)
        )
    }
}

@Composable
private fun AuthorInfo() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            painter = painterResource(R.drawable.author_photo),
            contentDescription = "Обложка автора"
        )
        Column(verticalArrangement = Arrangement.Center) {
            UiText(text = "Автор", color = Stroke, fontSize = 12.sp)
            UiText(text = "Merion Academy", color = White, fontSize = 16.sp)
        }
    }
}

@Composable
private fun AboutCourse(course: Course) {
    Column {
        UiText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            textAlign = TextAlign.Start,
            text = "О курсе",
            color = White,
            fontSize = 22.sp
        )
        Spacer(Modifier.padding(7.dp))
        UiText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            textAlign = TextAlign.Start,
            text = course.text,
            color = Stroke,
            fontSize = 14.sp
        )
    }
}

@Composable
private fun ActionButtons() {
    Column {
        UiButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            type = UiButtonType.Green(text = "Начать курс", onClick = {})
        )
        Spacer(Modifier.padding(4.dp))
        UiButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            type = UiButtonType.DarkGray(text = "Перейти на платформу", onClick = {})
        )
    }
}