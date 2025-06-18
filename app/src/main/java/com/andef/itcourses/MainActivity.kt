package com.andef.itcourses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.andef.itcourses.design.ui.theme.Dark
import com.andef.itcourses.design.ui.theme.ITCoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ITCoursesTheme(darkTheme = true) {
                Scaffold(containerColor = Dark, modifier = Modifier.fillMaxSize()) { innerPadding ->

                }
            }
        }
    }
}