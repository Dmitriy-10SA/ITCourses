package com.andef.itcourses.design.input.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.itcourses.design.R
import com.andef.itcourses.design.ui.theme.LightGray
import com.andef.itcourses.design.ui.theme.Stroke
import com.andef.itcourses.design.ui.theme.White

@Composable
fun UiInput(
    modifier: Modifier = Modifier,
    upText: String,
    placeholderText: String,
    value: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(space = 6.dp)) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            text = upText,
            color = White,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.roboto))
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            colors = textFieldColors(),
            value = value,
            placeholder = {
                Text(
                    text = placeholderText,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.roboto))
                )
            },
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            shape = shape,
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.roboto))
            ),
            onValueChange = onValueChange
        )
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
    focusedPlaceholderColor = Stroke,
    disabledPlaceholderColor = Stroke,
    unfocusedPlaceholderColor = Stroke
)

private val shape = RoundedCornerShape(size = 30.dp)