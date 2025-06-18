package com.andef.itcourses.design.button.ui

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import com.andef.itcourses.design.R
import com.andef.itcourses.design.button.type.UiButtonType
import com.andef.itcourses.design.text.ui.UiText
import com.andef.itcourses.design.ui.theme.DarkGray
import com.andef.itcourses.design.ui.theme.Green
import com.andef.itcourses.design.ui.theme.White

@Composable
fun UiButton(modifier: Modifier = Modifier, type: UiButtonType) {
    when (type) {
        is UiButtonType.DarkGray -> {
            ColorUiButton(
                modifier = modifier,
                text = type.text,
                onClick = type.onClick,
                containerColor = DarkGray
            )
        }

        is UiButtonType.Green -> {
            ColorUiButton(
                modifier = modifier,
                text = type.text,
                onClick = type.onClick,
                containerColor = Green
            )
        }

        UiButtonType.Odnoklassniki -> {
            OdnoklassnikiUiButton(modifier = modifier)
        }

        UiButtonType.VK -> {
            VKUiButton(modifier = modifier)
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
    val gradient = Brush.verticalGradient(colors = listOf(containerColor, containerColor))
    Button(
        modifier = modifier.background(brush = gradient, shape = shape),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = contentColor
        ),
        shape = shape,
        onClick = onClick
    ) {
        UiText(text = text, fontSize = 14.sp)
    }
}

@Composable
private fun OdnoklassnikiUiButton(modifier: Modifier) {
    val context = LocalContext.current
    val gradient = Brush.verticalGradient(colors = listOf(Color(0xFFF98509), Color(0xFFF95D00)))
    Button(
        modifier = modifier.background(brush = gradient, shape = shape),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = White
        ),
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, "https://ok.ru/".toUri())
            context.startActivity(intent)
        },
        shape = shape
    ) {
        Icon(
            painter = painterResource(R.drawable.odnoklassniki),
            contentDescription = "Значок одноклассников"
        )
    }
}

@Composable
private fun VKUiButton(modifier: Modifier) {
    val context = LocalContext.current
    val gradient = Brush.verticalGradient(colors = listOf(Color(0xFF2683ED), Color(0xFF2683ED)))
    Button(
        modifier = modifier.background(brush = gradient, shape = shape),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = White
        ),
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, "https://vk.com/".toUri())
            context.startActivity(intent)
        },
        shape = shape
    ) {
        Icon(
            painter = painterResource(R.drawable.vk),
            contentDescription = "Значок вк"
        )
    }
}

private val shape = RoundedCornerShape(size = 30.dp)