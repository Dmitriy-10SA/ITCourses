package com.andef.itcourses.design.bottombar.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.andef.itcourses.design.bottombar.item.UiBottomBarItem
import com.andef.itcourses.design.text.ui.UiText
import com.andef.itcourses.design.ui.theme.DarkGray
import com.andef.itcourses.design.ui.theme.Green
import com.andef.itcourses.design.ui.theme.LightGray
import com.andef.itcourses.design.ui.theme.Stroke
import com.andef.itcourses.design.ui.theme.White

@Composable
fun UiBottomBar(
    onItemClick: (UiBottomBarItem) -> Unit,
    selectedItem: (UiBottomBarItem) -> Boolean,
    items: List<UiBottomBarItem>,
    isVisible: Boolean
) {
    if (isVisible) {
        Column {
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.5.dp,
                color = Stroke
            )
            NavigationBar(
                containerColor = DarkGray,
                contentColor = White
            ) {
                items.forEach { item ->
                    NavigationBarItem(
                        selected = selectedItem(item),
                        onClick = { onItemClick(item) },
                        icon = {
                            Icon(
                                painter = painterResource(item.iconResId),
                                contentDescription = item.contentDescription
                            )
                        },
                        label = { UiText(text = item.text, fontSize = 12.sp) },
                        colors = navigationBarItemColors()
                    )
                }
            }
        }
    }
}

@Composable
private fun navigationBarItemColors() = NavigationBarItemDefaults.colors(
    selectedTextColor = Green,
    selectedIconColor = Green,
    indicatorColor = LightGray,
    unselectedTextColor = White,
    unselectedIconColor = White,
    disabledTextColor = White,
    disabledIconColor = White
)