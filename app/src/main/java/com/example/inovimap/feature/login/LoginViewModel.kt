package com.example.inovimap.feature.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inovimap.domain.login.usecases.GetServerResponse
import com.example.inovimap.domain.login.usecases.ValidateEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginState(
    val user: String = "",
    val password: String = ""
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateEmail: ValidateEmail,
    private val getServerResponse: GetServerResponse
) : ViewModel() {

    private val _state = MutableStateFlow(LoginState())
    val state = _state.asStateFlow()

    private val _events = Channel<Event>()
    val events = _events.receiveAsFlow()

    fun setUser(user: String) {
        _state.update { it.copy(user = user) }
    }

    fun setPassword(password: String) {
        _state.update { it.copy(password = password) }
    }

    fun navigateToMap() {
        viewModelScope.launch(Dispatchers.IO) {
            val user = _state.value.user
            val password = _state.value.password
            val validatedUser = validateEmail(user)
            if (validatedUser == null) {
                if (password.isBlank()) {
                    _events.send(Event.ShowMessage("La contraseña no puede estar vacía"))
                } else {
                    val serverResponse = getServerResponse(email = user, password = password)
                    val messageResponse = serverResponse.message
                    _events.send(Event.ShowMessage(messageResponse))
                    serverResponse.user?.let {
                        _events.send(
                            Event.NavigateToMap(
                                latitude = it.latitude,
                                longitude = it.longitude
                            )
                        )
                    }
                }
            } else {
                _events.send(Event.ShowMessage(validatedUser))
            }
        }
    }

    sealed class Event {
        data class NavigateToMap(val latitude: Double, val longitude: Double) : Event()
        data class ShowMessage(val message: String) : Event()
    }
}