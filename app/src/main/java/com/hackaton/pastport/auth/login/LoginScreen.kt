package com.hackaton.pastport.auth.login

import android.widget.Toast
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hackaton.pastport.R
import com.hackaton.pastport.auth.login.viewmodel.LoginViewModel
import com.hackaton.pastport.ui.AuthTitle
import com.hackaton.pastport.ui.PastPortButton
import com.hackaton.pastport.ui.PastPortInput
import com.hackaton.pastport.ui.PastPortPasswordInput
import com.hackaton.pastport.ui.theme.Gray700
import com.hackaton.pastport.ui.theme.Main
import com.hackaton.pastport.ui.theme.PastPortFontStyle
import com.hackaton.pastport.ui.utils.addFocusCleaner
import com.hackaton.pastport.ui.utils.noRippleClickable

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navToMain: () -> Unit,
    navToSignUp: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val id = viewModel.id
    val password = viewModel.password

    val isLoading = viewModel.isLoading
    val isLoginSuccess = viewModel.isLoginSuccess

    if (isLoginSuccess == true) {
        navToMain()
    } else if (isLoginSuccess == false) {
        Toast.makeText(context, stringResource(R.string.login_fail), Toast.LENGTH_SHORT).show()
    }

    Box(
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
        Column (
            modifier = modifier
                .align(Alignment.TopCenter)
                .padding(
                    start = 40.dp,
                    end = 40.dp
                )
                .fillMaxSize()
                .addFocusCleaner(focusManager),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthTitle(title = "Login")
            PastPortInput(
                label = stringResource(R.string.auth_id),
                input = id,
                hint = stringResource(R.string.auth_id_hint),
                imeAction = ImeAction.Next,
                onValueChange = { input ->
                    viewModel.onIdChange(input)
                }
            )
            PastPortPasswordInput(
                label = stringResource(R.string.auth_pw),
                input = password,
                hint = stringResource(R.string.auth_pw_hint),
                imeAction = ImeAction.Done,
                onValueChange = { input ->
                    viewModel.onPasswordChange(input)
                }
            )
            Spacer(modifier.height(26.dp))
            PastPortButton(
                modifier = modifier.fillMaxWidth(),
                text = stringResource(R.string.login),
                loading = isLoading,
                onClick = {
                    viewModel.onLoginClick()
                }
            )
            IsMember(
                navToSignUp = navToSignUp
            )
        }
    }
}

@Composable
private fun IsMember(
    modifier: Modifier = Modifier,
    navToSignUp: () -> Unit
) {
    Row (
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = stringResource(R.string.login_is_not_account),
            style = PastPortFontStyle.medium14,
            color = Gray700
        )
        Text(
            modifier = modifier
                .noRippleClickable {
                    navToSignUp()
                },
            text = stringResource(R.string.signup),
            style = PastPortFontStyle.bold15,
            color = Main,
            textDecoration = TextDecoration.Underline
        )
    }
}