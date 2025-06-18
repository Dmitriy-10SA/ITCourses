package com.andef.itcourses.presentation.allcourses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andef.itcourses.domain.usecases.LoadCoursesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllCoursesViewModel @Inject constructor(
    private val loadCoursesUseCase: LoadCoursesUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(AllCoursesState())
    val state: StateFlow<AllCoursesState> = _state

    fun send(intent: AllCoursesIntent) {
        when (intent) {
            AllCoursesIntent.LoadCourses -> {
                loadCourses()
            }
        }
    }

    private fun loadCourses() {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(isLoading = true)
                _state.value = _state.value.copy(courses = loadCoursesUseCase.invoke())
            } catch (_: Exception) {
                _state.value = _state.value.copy(isError = true)
            } finally {
                _state.value = _state.value.copy(isLoading = false)
            }
        }
    }
}