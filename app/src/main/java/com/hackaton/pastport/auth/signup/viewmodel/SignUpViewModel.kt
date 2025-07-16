package com.hackaton.pastport.auth.signup.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackaton.pastport.R
import com.hackaton.pastport.auth.signup.repository.SignUpRepository
import com.hackaton.pastport.ui.theme.Black
import com.hackaton.pastport.ui.theme.Main
import com.hackaton.pastport.ui.theme.Red
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: SignUpRepository
) : ViewModel() {
    var id by mutableStateOf("")
    var password by mutableStateOf("")
    var checkPassword by mutableStateOf("")

    var isLoading by mutableStateOf(false)
    var isDuplicateIdSuccess by mutableStateOf<Boolean?>(null)
    var isSignUpSuccess by mutableStateOf<Boolean?>(null)

    var idErrorMessage by mutableIntStateOf(R.string.none)
    var pwCheckErrorMessage by mutableIntStateOf(R.string.none)
    var errorMessageColor by mutableStateOf(Black)

    fun onIdChange(input: String) {
        id = input
        if (isDuplicateIdSuccess != null) {
            isDuplicateIdSuccess = null
            idErrorMessage = R.string.none
        }
    }
    fun onPasswordChange(input: String) {
        password = input
        if (pwCheckErrorMessage != R.string.none) {
            pwCheckErrorMessage = R.string.none
        }
    }
    fun onCheckPasswordChange(input: String) {
        checkPassword = input
        if (pwCheckErrorMessage != R.string.none) {
            pwCheckErrorMessage = R.string.none
        }
    }

    fun onDuplicateIdClick() {
        viewModelScope.launch {
            isLoading = true
            val result = repository.duplicateId(id)
            result.onSuccess {
                if (it.data.available) {
                    isDuplicateIdSuccess = true
                    idErrorMessage = R.string.signup_can_use
                    errorMessageColor = Main
                } else {
                    isDuplicateIdSuccess = false
                    idErrorMessage = R.string.signup_cant_use
                    errorMessageColor = Red
                }
            }.onFailure {
                isDuplicateIdSuccess = false
                idErrorMessage = R.string.signup_cant_use
                errorMessageColor = Red
            }
            isLoading = false
        }
    }

    fun onSignUpClick() {
        if (isDuplicateIdSuccess != true) {
            idErrorMessage = R.string.signup_please_check
            errorMessageColor = Red
            return
        }

        if (password != checkPassword) {
            pwCheckErrorMessage = R.string.signup_not_same_pw
            return
        }

        viewModelScope.launch {
            isLoading = true
            val result = repository.signUp(id, password)
            result.onSuccess {
                isSignUpSuccess = true
            }.onFailure {
                isSignUpSuccess = false
            }
            isLoading = false
        }
    }
}