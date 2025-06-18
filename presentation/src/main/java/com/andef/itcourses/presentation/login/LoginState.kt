package com.andef.itcourses.presentation.login

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isCorrectState: Boolean = false
)
