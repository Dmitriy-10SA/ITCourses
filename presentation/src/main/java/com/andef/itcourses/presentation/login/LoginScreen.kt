package com.andef.itcourses.presentation.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.andef.itcourses.ViewModelFactory
import com.andef.itcourses.design.button.type.UiButtonType
import com.andef.itcourses.design.button.ui.UiButton
import com.andef.itcourses.design.input.ui.UiInput
import com.andef.itcourses.design.text.ui.UiText
import com.andef.itcourses.design.ui.theme.Green
import com.andef.itcourses.design.ui.theme.Stroke
import com.andef.itcourses.design.ui.theme.White

@Composable
fun LoginScreen(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
    viewModelFactory: ViewModelFactory
) {
    val viewModel: LoginViewModel = viewModel(factory = viewModelFactory)
    val state = viewModel.state.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .padding(horizontal = 16.dp)
            .navigationBarsPadding()
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item { Spacer(modifier = Modifier.padding(4.dp)) }
        item {
            UiText(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                text = "Вход",
                color = White,
                fontSize = 28.sp
            )
        }
        item { Spacer(modifier = Modifier.padding(12.dp)) }
        item { EmailAndPasswordInput(state = state, viewModel = viewModel) }
        item { Spacer(modifier = Modifier.padding(10.dp)) }
        item { ActionButtons(viewModel = viewModel, navHostController = navHostController) }
        item { Spacer(modifier = Modifier.padding(4.dp)) }
    }
}

@Composable
private fun EmailAndPasswordInput(
    state: State<LoginState>,
    viewModel: LoginViewModel
) {
    UiInput(
        modifier = Modifier.fillMaxWidth(),
        upText = "Email",
        placeholderText = "example@gmail.com",
        value = state.value.email,
        onValueChange = {
            val filteredText = it.filter { char ->
                char !in 'а'..'я' &&
                        char !in 'А'..'Я' && char != 'ё' && char != 'Ё'
            }
            if (filteredText.length == it.length) {
                viewModel.send(LoginIntent.EmailInput(filteredText))
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        )
    )
    Spacer(modifier = Modifier.padding(6.dp))
    UiInput(
        modifier = Modifier.fillMaxWidth(),
        upText = "Пароль",
        placeholderText = "Введите пароль",
        value = state.value.password,
        onValueChange = { viewModel.send(LoginIntent.PasswordInput(it)) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        )
    )
}

@Composable
private fun ActionButtons(navHostController: NavHostController, viewModel: LoginViewModel) {
    val keyboard = LocalSoftwareKeyboardController.current
    UiButton(
        modifier = Modifier.fillMaxWidth(),
        type = UiButtonType.Green(
            text = "Вход",
            onClick = {
                keyboard?.hide()
                viewModel.send(
                    LoginIntent.Login(
                        onSuccess = { route ->
                            navHostController.navigate(route) { popUpTo(0) }
                        }
                    )
                )
            }
        )
    )
    Spacer(modifier = Modifier.padding(7.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        UiText(text = "Нету аккаунта?", color = White, fontSize = 12.sp)
        Spacer(modifier = Modifier.padding(4.dp))
        UiText(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(30.dp))
                .clickable(
                    onClick = {
                        //TODO("Данную кнопку делать не нужно")
                    }
                ),
            text = "Регистрация",
            color = Green,
            fontSize = 12.sp
        )
    }
    Spacer(modifier = Modifier.padding(1.dp))
    UiText(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(30.dp))
            .clickable(
                onClick = {
                    //TODO("Данную кнопку делать не нужно")
                }
            ),
        text = "Забыл пароль",
        color = Green,
        fontSize = 12.sp
    )
    Spacer(modifier = Modifier.padding(12.dp))
    HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Stroke, thickness = 1.dp)
    Spacer(modifier = Modifier.padding(14.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        UiButton(modifier = Modifier.weight(1f), type = UiButtonType.VK)
        UiButton(modifier = Modifier.weight(1f), type = UiButtonType.Odnoklassniki)
    }

}