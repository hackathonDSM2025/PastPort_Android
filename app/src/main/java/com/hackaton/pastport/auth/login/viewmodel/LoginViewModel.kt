package com.hackaton.pastport.auth.login.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackaton.pastport.auth.login.repository.LoginRepository
import com.hackaton.pastport.network.model.auth.SignRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {
    var id by mutableStateOf("")
    var password by mutableStateOf("")

    var isLoading by mutableStateOf(false)
    var isLoginSuccess by mutableStateOf<Boolean?>(null)

    fun onIdChange(input: String) {
        id = input
    }
    fun onPasswordChange(input: String) {
        password = input
    }

    fun onLoginClick() {
        if (id.isEmpty() || password.isEmpty()) {
            return
        }

        viewModelScope.launch {
            isLoading = true
            val result = repository.login(SignRequest(id, password))
            result.onSuccess {
                isLoginSuccess = true
            }.onFailure {
                isLoginSuccess = false
            }
            isLoading = false
        }
    }
}