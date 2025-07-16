package com.hackaton.pastport.auth.signup

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hackaton.pastport.R
import com.hackaton.pastport.auth.signup.viewmodel.SignUpViewModel
import com.hackaton.pastport.ui.AuthTitle
import com.hackaton.pastport.ui.InputTextButton
import com.hackaton.pastport.ui.PastPortButton
import com.hackaton.pastport.ui.PastPortInput
import com.hackaton.pastport.ui.PastPortPasswordInput
import com.hackaton.pastport.ui.utils.addFocusCleaner

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navToMain: () -> Unit,
    navToBack: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val id = viewModel.id
    val password = viewModel.password
    val checkPassword = viewModel.checkPassword

    val isLoading = viewModel.isLoading
    val isSignUpSuccess = viewModel.isSignUpSuccess

    val idErrorMessage = viewModel.idErrorMessage
    val pwCheckErrorMessage = viewModel.pwCheckErrorMessage
    val errorMessageColor = viewModel.errorMessageColor

    if (isSignUpSuccess == true) {
        navToMain()
    } else if (isSignUpSuccess == false) {
        Toast.makeText(context, stringResource(R.string.signup_fail), Toast.LENGTH_SHORT).show()
    }

    BackHandler {
        navToBack()
    }

    Box (
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            painter = painterResource(R.drawable.auth_background),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )

        Column(
            modifier = modifier
                .align(Alignment.TopCenter)
                .padding(
                    start = 40.dp,
                    end = 40.dp
                )
                .fillMaxSize()
                .addFocusCleaner(focusManager)
        ) {
            AuthTitle(title = "Sign Up")
            SignUpIdInput(
                modifier = modifier,
                id = id,
                idErrorMessage = stringResource(idErrorMessage),
                errorMessageColor = errorMessageColor,
                isLoading = isLoading,
                onClick = {
                    if (id.isNotEmpty()) {
                        viewModel.onDuplicateIdClick()
                    }
                },
                onValueChange = { input ->
                    viewModel.onIdChange(input)
                }
            )
            PastPortPasswordInput(
                modifier = modifier.fillMaxWidth(),
                label = stringResource(R.string.auth_pw),
                input = password,
                hint = stringResource(R.string.auth_pw_hint),
                imeAction = ImeAction.Next,
                onValueChange = { input ->
                    viewModel.onPasswordChange(input)
                }
            )
            PastPortPasswordInput(
                modifier = modifier.fillMaxWidth(),
                label = stringResource(R.string.auth_check_pw),
                input = checkPassword,
                hint = stringResource(R.string.auth_check_pw_hint),
                imeAction = ImeAction.Done,
                errorMessage = stringResource(pwCheckErrorMessage),
                onValueChange = { input ->
                    viewModel.onCheckPasswordChange(input)
                }
            )
            Spacer(modifier.height(26.dp))
            PastPortButton(
                modifier = modifier.fillMaxWidth(),
                text = stringResource(R.string.signup),
                loading = isLoading,
                onClick = {
                    viewModel.onSignUpClick()
                }
            )
        }
    }
}

@Composable
fun SignUpIdInput(
    modifier: Modifier = Modifier,
    id: String,
    idErrorMessage: String,
    errorMessageColor: Color,
    isLoading: Boolean,
    onClick: () -> Unit,
    onValueChange: (String) -> Unit
) {
    Row (
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        PastPortInput(
            modifier = modifier.fillMaxWidth(0.75f),
            label = stringResource(R.string.auth_id),
            input = id,
            hint = stringResource(R.string.auth_id_hint),
            imeAction = ImeAction.Next,
            errorMessage = idErrorMessage,
            errorMessageColor = errorMessageColor,
            onValueChange = onValueChange
        )
        InputTextButton(
            loading = isLoading,
            buttonText = stringResource(R.string.signup_check_duplicate),
            onClick = onClick
        )
    }
}