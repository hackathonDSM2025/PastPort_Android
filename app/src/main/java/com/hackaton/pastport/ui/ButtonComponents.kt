package com.hackaton.pastport.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hackaton.pastport.ui.theme.Main
import com.hackaton.pastport.ui.theme.PastPortFontStyle
import com.hackaton.pastport.ui.theme.White
import com.hackaton.pastport.ui.utils.noRippleClickable


@Composable
fun PastPortButton(
    modifier: Modifier = Modifier,
    text: String,
    loading: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp),
        shape = RoundedCornerShape(10.dp),
        onClick = onClick,
        enabled = !loading,
        colors = ButtonColors(
            disabledContainerColor = Main,
            disabledContentColor = White,
            containerColor = Main,
            contentColor = White
        )
    ) {
        if (loading) {
            CircularProgressIndicator()
        } else {
            Text(
                text = text,
                style = PastPortFontStyle.bold16,
                color = White
            )
        }
    }
}

@Composable
fun InputTextButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    buttonText: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .width(70.dp)
            .height(40.dp)
            .background(
                color = Main,
                shape = RoundedCornerShape(8.dp)
            )
            .noRippleClickable {
                if (!isLoading) {
                    onClick()
                }
            }
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = modifier.align(Alignment.Center)
            )
        } else {
            Text(
                modifier = modifier.align(Alignment.Center),
                text = buttonText,
                style = PastPortFontStyle.medium14,
                color = White
            )
        }
    }
}
