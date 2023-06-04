package com.example.gestion_sispac.ui.theme.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestion_sispac.ui.theme.repository.RepositoryUser
import com.example.gestion_sispac.ui.theme.response.LoginResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginModel: ViewModel() {
    var cif by mutableStateOf("")
    var password by mutableStateOf("")
    var _state = MutableStateFlow(UIState())
    val state: StateFlow<UIState> = _state.asStateFlow()

    val repository = RepositoryUser()
    data class UIState(
        val _loading: Boolean = false,
        val loginResponse: LoginResponse = LoginResponse()
    )
    fun onSubmit() {
        viewModelScope.launch {
            _state.update { it.copy(_loading = true) }
            val login = repository.fetchData(cif, password).getOrDefault(LoginResponse())
            _state.update { it.copy(loginResponse = login) }
            _state.update { it.copy(_loading = false) }
        }
    }
}