package com.andef.itcourses.design.button.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.itcourses.design.R
import com.andef.itcourses.design.button.type.UiButtonType
import com.andef.itcourses.design.ui.theme.DarkGray
import com.andef.itcourses.design.ui.theme.Green
import com.andef.itcourses.design.ui.theme.White

@Composable
fun UiButton(
    modifier: Modifier = Modifier,
    text: String,
    type: UiButtonType,
    onClick: () -> Unit
) {
    when (type) {
        UiButtonType.DarkGray -> {
            ColorUiButton(
                modifier = modifier,
                text = text,
                onClick = onClick,
                containerColor = DarkGray
            )
        }

        UiButtonType.Green -> {
            ColorUiButton(
                modifier = modifier,
                text = text,
                onClick = onClick,
                containerColor = Green
            )
        }

        UiButtonType.Odnoklassniki -> {
            OdnoklassnikiUiButton()
        }

        UiButtonType.VK -> {
            VKUiButton()
        }
    }
}

@Composable
private fun ColorUiButton(
    modifier: Modifier = Modifier,
    text: String,
    containerColor: Color,
    contentColor: Color = White,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = containerColor, contentColor),
        shape = shape,
        onClick = onClick
    ) {
        Text(text = text, fontSize = 14.sp, fontFamily = FontFamily(Font(R.font.roboto)))
    }
}

@Composable
private fun OdnoklassnikiUiButton() {

}

@Composable
private fun VKUiButton() {

}

private val shape = RoundedCornerShape(size = 30.dp)