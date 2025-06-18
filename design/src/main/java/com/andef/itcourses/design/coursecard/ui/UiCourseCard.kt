package com.andef.itcourses.design.coursecard.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.itcourses.design.R
import com.andef.itcourses.design.dateFormatter
import com.andef.itcourses.design.text.ui.UiText
import com.andef.itcourses.design.ui.theme.DarkGray
import com.andef.itcourses.design.ui.theme.Green
import com.andef.itcourses.design.ui.theme.Stroke
import com.andef.itcourses.design.ui.theme.White
import com.andef.itcourses.domain.Course
import kotlinx.datetime.toJavaLocalDate

@Composable
fun UiCourseCard(
    modifier: Modifier = Modifier,
    course: Course,
    onLikeClick: (Boolean) -> Unit,
    onMoreDetailedClick: () -> Unit
) {
    val image = if (course.id == 100 || course.id == 103) {
        R.drawable.course_photo1
    } else if (course.id == 101 || course.id == 104) {
        R.drawable.course_photo2
    } else {
        R.drawable.course_photo3
    }
    Card(
        modifier = modifier,
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = DarkGray,
            contentColor = White
        )
    ) {
        MainContent(
            image = image,
            course = course,
            onMoreDetailedClick = onMoreDetailedClick,
            onLikeClick = onLikeClick
        )
    }
}

@Composable
private fun MainContent(
    image: Int,
    course: Course,
    onMoreDetailedClick: () -> Unit,
    onLikeClick: (Boolean) -> Unit,
) {
    Column {
        Box {
            Image(
                modifier = Modifier
                    .clip(shape = shape)
                    .fillMaxWidth()
                    .height(130.dp),
                contentScale = ContentScale.Crop,
                painter = painterResource(image),
                contentDescription = "Обложка курса"
            )
            OnImageRatingAndDateWithLikeFlag(course = course, onLikeClick = onLikeClick)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                TitleAndText(course = course)
                PriceAndMoreDetailButton(course = course, onMoreDetailedClick = onMoreDetailedClick)
            }
        }
    }
}

@Composable
private fun BoxScope.OnImageRatingAndDateWithLikeFlag(
    course: Course,
    onLikeClick: (Boolean) -> Unit,
) {
    IconButton(
        modifier = Modifier.align(Alignment.TopEnd),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Color(0xFF32333A).copy(alpha = 0.8f)
        ),
        onClick = {
            onLikeClick(!course.hasLike)
        }
    ) {
        Icon(
            tint = if (course.hasLike) Green else White,
            painter = painterResource(R.drawable.like_flag),
            contentDescription = "Флажок добавления в избранное"
        )
    }
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
                    shape = shape
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
                    shape = shape
                )
                .padding(horizontal = 6.dp, vertical = 1.dp),
            fontSize = 12.sp,
            color = White,
            text = course.startDate.toJavaLocalDate().format(dateFormatter)
        )
    }
}

@Composable
private fun ColumnScope.TitleAndText(course: Course) {
    UiText(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Left,
        text = course.title,
        fontSize = 16.sp,
        color = White
    )
    UiText(
        text = course.text,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Left,
        fontSize = 12.sp,
        color = Stroke,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun ColumnScope.PriceAndMoreDetailButton(course: Course, onMoreDetailedClick: () -> Unit) {
    Row {
        UiText(text = "${course.price} ₽", fontSize = 16.sp, color = White)
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier
                .clip(shape = shape)
                .clickable(onClick = onMoreDetailedClick),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            UiText(text = "Подробнее", fontSize = 12.sp, color = Green)
            Icon(
                tint = Green,
                painter = painterResource(R.drawable.right_arrow),
                contentDescription = "Стрелка вправо"
            )
        }
    }
}

private val shape = RoundedCornerShape(size = 16.dp)