package com.andef.itcourses.design.error.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.itcourses.design.button.type.UiButtonType
import com.andef.itcourses.design.button.ui.UiButton
import com.andef.itcourses.design.text.ui.UiText
import com.andef.itcourses.design.ui.theme.White

@Composable
fun UiError(modifier: Modifier = Modifier, onRetryClick: () -> Unit) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UiText(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Упс, ошибка!",
            fontSize = 16.sp,
            color = White
        )
        Spacer(modifier = Modifier.padding(8.dp))
        UiButton(
            modifier = Modifier.fillMaxWidth(),
            type = UiButtonType.Green(text = "Повторить", onClick = onRetryClick)
        )
    }
}