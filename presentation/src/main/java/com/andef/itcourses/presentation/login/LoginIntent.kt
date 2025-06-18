package com.andef.itcourses.presentation.login

sealed class LoginIntent {
    data class EmailInput(val email: String) : LoginIntent()
    data class PasswordInput(val password: String) : LoginIntent()
    data class Login(val onSuccess: (String) -> Unit) : LoginIntent()
}