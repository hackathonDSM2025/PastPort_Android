package com.hackaton.pastport.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hackaton.pastport.R
import com.hackaton.pastport.ui.theme.Black
import com.hackaton.pastport.ui.theme.Main
import com.hackaton.pastport.ui.theme.PastPortFontStyle
import com.hackaton.pastport.ui.theme.White
import com.hackaton.pastport.ui.utils.noRippleClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PastPortTopBar(
    modifier: Modifier = Modifier,
    text: String,
    isBack: Boolean = false,
    backPress: () -> Unit = {}
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    CenterAlignedTopAppBar(
        modifier = modifier
            .background(White)
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .drawBehind {
                drawLine(
                    color = Main,
                    strokeWidth = 2.dp.toPx(),
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height)
                )
            },
        title = {
            Text(
                text = text,
                style = PastPortFontStyle.semi20
            )
        },
        navigationIcon = {
            if (isBack) {
                Icon(
                    modifier = modifier.noRippleClickable { backPress() },
                    painter = painterResource(R.drawable.icon_back_arrow),
                    contentDescription = "back arrow"
                )
            }
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = White,
            titleContentColor = Black
        )
    )
}

@Composable
fun AuthTitle(
    modifier: Modifier = Modifier,
    title: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = modifier.height(100.dp))
        Text(
            text = title,
            style = PastPortFontStyle.bold36,
            color = Main,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = modifier.height(50.dp))
    }
}