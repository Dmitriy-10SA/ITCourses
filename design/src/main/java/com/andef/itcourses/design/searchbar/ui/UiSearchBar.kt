package com.andef.itcourses.design.searchbar.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.itcourses.design.R
import com.andef.itcourses.design.ui.theme.Green
import com.andef.itcourses.design.ui.theme.LightGray
import com.andef.itcourses.design.ui.theme.Stroke
import com.andef.itcourses.design.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UiSearchBar(
    modifier: Modifier = Modifier,
    placeholderText: String,
    value: String,
    onValueChange: (String) -> Unit,
    isWithSortByDate: Boolean,
    onSortByDateClick: () -> Unit,
    onFilterButtonClick: () -> Unit
) {
    var isSortByDateActive by remember { mutableStateOf(false) }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        SearchBarAndFilter(
            placeholderText = placeholderText,
            value = value,
            onValueChange = onValueChange,
            onFilterButtonClick = onFilterButtonClick
        )
        if (isWithSortByDate) {
            SortByDateButton(
                modifier = modifier,
                isSortByDateActive = isSortByDateActive,
                onClick = {
                    isSortByDateActive = !isSortByDateActive
                    onSortByDateClick()
                }
            )
        }
    }
}

@Composable
private fun SearchBarAndFilter(
    placeholderText: String,
    value: String,
    onValueChange: (String) -> Unit,
    onFilterButtonClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TextField(
            modifier = Modifier
                .height(55.dp)
                .weight(1f),
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.search),
                    contentDescription = "Значок лупы"
                )
            },
            placeholder = {
                Text(
                    text = placeholderText,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto))
                )
            },
            shape = shape,
            colors = textFieldColors()
        )
        IconButton(
            modifier = Modifier.size(55.dp),
            onClick = onFilterButtonClick,
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = LightGray,
                contentColor = White
            )
        ) {
            Icon(
                painter = painterResource(R.drawable.filter),
                contentDescription = "Значок фильтра"
            )
        }
    }
}

@Composable
private fun SortByDateButton(
    modifier: Modifier = Modifier,
    isSortByDateActive: Boolean,
    onClick: () -> Unit
) {
    Box(modifier = modifier, contentAlignment = Alignment.CenterEnd) {
        Row(
            modifier = Modifier
                .clip(shape = shape)
                .clickable(onClick = onClick),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "По дате добавления",
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.roboto)),
                color = Green,
                textDecoration = if (isSortByDateActive) {
                    TextDecoration.Underline
                } else {
                    TextDecoration.None
                }
            )
            Icon(
                painter = painterResource(R.drawable.sort_by_date),
                contentDescription = "Значок сортировки по дате",
                tint = Green
            )
        }
    }
}

@Composable
private fun textFieldColors() = TextFieldDefaults.colors(
    focusedTextColor = White,
    unfocusedTextColor = White,
    disabledTextColor = White,
    focusedContainerColor = LightGray,
    unfocusedContainerColor = LightGray,
    disabledContainerColor = LightGray,
    cursorColor = White,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    focusedLeadingIconColor = White,
    disabledLeadingIconColor = White,
    unfocusedLeadingIconColor = White,
    focusedPlaceholderColor = Stroke,
    disabledPlaceholderColor = Stroke,
    unfocusedPlaceholderColor = Stroke
)

private val shape = RoundedCornerShape(size = 30.dp)