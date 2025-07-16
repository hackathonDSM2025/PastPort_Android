package com.hackaton.pastport.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.hackaton.pastport.R
import com.hackaton.pastport.ui.theme.Black
import com.hackaton.pastport.ui.theme.Gray300
import com.hackaton.pastport.ui.theme.Gray50
import com.hackaton.pastport.ui.theme.Main
import com.hackaton.pastport.ui.theme.PastPortFontStyle
import com.hackaton.pastport.ui.theme.Red
import com.hackaton.pastport.ui.utils.noRippleClickable

@Composable
private fun PastPortBasicTextField(
    modifier: Modifier = Modifier,
    input: String,
    hint: String,
    imeAction: ImeAction,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: (@Composable (() -> Unit))? = null,
    onValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = Gray50,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 12.dp,
                bottom = 12.dp
            )
    ) {
        BasicTextField(
            modifier = modifier
                .align(Alignment.CenterStart)
                .fillMaxWidth()
                .padding(
                    end = if (trailingIcon != null) 24.dp else 0.dp
                ),
            value = input,
            onValueChange = onValueChange,
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus(true) },
                onNext = { focusManager.moveFocus(FocusDirection.Next) }
            ),
            visualTransformation = visualTransformation,
            textStyle = PastPortFontStyle.medium14,
            cursorBrush = SolidColor(Main),
            decorationBox = { innerTextField ->
                if (input.isEmpty()) {
                    Text(
                        text = hint,
                        style = PastPortFontStyle.medium14,
                        color = Gray300
                    )
                }
                innerTextField()
            }
        )
        trailingIcon?.let {
            Box(modifier = Modifier.align(Alignment.CenterEnd)) { it() }
        }
    }
}

@Composable
private fun PastPortTextField(
    modifier: Modifier = Modifier,
    input: String,
    hint: String,
    imeAction: ImeAction,
    onValueChange: (String) -> Unit
) {
    PastPortBasicTextField(
        modifier = modifier,
        input = input,
        hint = hint,
        imeAction = imeAction,
        keyboardType = KeyboardType.Text,
        onValueChange = onValueChange
    )
}

@Composable
private fun PastPortPasswordTextField(
    modifier: Modifier = Modifier,
    input: String,
    hint: String,
    imeAction: ImeAction,
    onValueChange: (String) -> Unit
) {
    var isShowPassword by remember { mutableStateOf(false) }

    PastPortBasicTextField(
        modifier = modifier,
        input = input,
        hint = hint,
        imeAction = imeAction,
        keyboardType = KeyboardType.Password,
        onValueChange = onValueChange,
        visualTransformation = if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            Icon(
                modifier = Modifier
                    .size(20.dp)
                    .noRippleClickable {
                        isShowPassword = !isShowPassword
                    },
                painter = painterResource(if (isShowPassword) R.drawable.icon_show_password else R.drawable.icon_hide_password),
                contentDescription = if (isShowPassword) "Hide Password Icon" else "Show Password Icon"
            )
        }
    )
}

@Composable
private fun PastPortBasicInput(
    modifier: Modifier = Modifier,
    label: String,
    errorMessage: String = "",
    errorMessageColor: Color = Black,
    textField: @Composable () -> Unit
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = label,
            style = PastPortFontStyle.medium14
        )
        textField()
        Text(
            modifier = modifier
                .padding(
                    start = 4.dp,
                    bottom = 4.dp
                ),
            text = errorMessage,
            style = PastPortFontStyle.medium10,
            color = errorMessageColor
        )
    }
}

@Composable
fun PastPortInput(
    modifier: Modifier = Modifier,
    label: String,
    input: String,
    hint: String,
    imeAction: ImeAction,
    errorMessage: String = "",
    errorMessageColor: Color = Black,
    onValueChange: (String) -> Unit
) {
    PastPortBasicInput(
        modifier = modifier,
        label = label,
        errorMessage = errorMessage,
        errorMessageColor = errorMessageColor,
        textField = {
            PastPortTextField(
                modifier = modifier,
                input = input,
                hint = hint,
                imeAction = imeAction,
                onValueChange = onValueChange
            )
        }
    )
}

@Composable
fun PastPortPasswordInput(
    modifier: Modifier = Modifier,
    label: String,
    input: String,
    hint: String,
    imeAction: ImeAction,
    errorMessage: String = "",
    onValueChange: (String) -> Unit
) {
    PastPortBasicInput(
        modifier = modifier,
        label = label,
        errorMessage = errorMessage,
        errorMessageColor = Red,
        textField = {
            PastPortPasswordTextField(
                modifier = modifier,
                input = input,
                hint = hint,
                imeAction = imeAction,
                onValueChange = onValueChange
            )
        }
    )
}