package com.example.inovimap.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginState(
    val user: String = "",
    val password: String = ""
)

class LoginViewModel: ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _events = Channel<Event>()
    val events = _events.receiveAsFlow()

    fun setUser(user: String) {
        _state.update { it.copy(user = user) }
    }

    fun setPassword(password: String) {
        _state.update { it.copy(password =  password) }
    }

    fun navigateToMap() {
        viewModelScope.launch(Dispatchers.IO) {
            _events.send(Event.NavigateToMap)
        }
    }

    sealed class Event{
        data object NavigateToMap: Event()
    }
}
