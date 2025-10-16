package com.example.inovimap.feature.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class LoginState(
    val user: String = "",
    val password: String = ""
)

class LoginViewModel: ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    fun setUser(user: String) {
        _state.update { it.copy(user = user) }
    }

    fun setPassword(password: String) {
        _state.update { it.copy(password =  password) }
    }
}
