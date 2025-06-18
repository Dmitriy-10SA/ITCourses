package com.andef.itcourses.presentation.login

import androidx.lifecycle.ViewModel
import com.andef.itcourses.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state

    fun send(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.EmailInput -> {
                changeInput(email = intent.email)
            }

            is LoginIntent.Login -> {
                login(onSuccess = intent.onSuccess)
            }

            is LoginIntent.PasswordInput -> {
                changeInput(password = intent.password)
            }
        }
    }

    private fun login(onSuccess: (String) -> Unit) {
        if (_state.value.isCorrectState) {
            onSuccess(Screen.MainScreens.route)
        }
    }

    private fun changeInput(
        email: String = _state.value.email,
        password: String = _state.value.password
    ) {
        _state.value = _state.value.copy(
            email = email,
            password = password,
            isCorrectState = isValidEmail(email) && password.isNotEmpty()
        )
    }

    private fun isValidEmail(email: String): Boolean {
        val pattern = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$".toRegex()
        return pattern.matches(email)
    }
}