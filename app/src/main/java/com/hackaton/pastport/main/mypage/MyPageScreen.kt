package com.hackaton.pastport.main.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hackaton.pastport.R
import com.hackaton.pastport.main.mypage.viewmodel.MyPageViewModel
import com.hackaton.pastport.ui.PastPortTopBar
import com.hackaton.pastport.ui.theme.Gray600
import com.hackaton.pastport.ui.theme.Main
import com.hackaton.pastport.ui.theme.PastPortFontStyle
import com.hackaton.pastport.ui.theme.Red
import com.hackaton.pastport.ui.utils.noRippleClickable

@Composable
fun MyPageScreen(
    modifier: Modifier = Modifier,
    viewModel: MyPageViewModel = hiltViewModel(),
    navToAuth: () -> Unit,
    navToMedal: () -> Unit,
    navToReport: () -> Unit,
    navToLanguage: () -> Unit
) {
    val username = viewModel.username

    val isLoading = viewModel.isLoading

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = modifier.align(Alignment.Center)
            )
        } else {
            Image(
                modifier = modifier
                    .align(Alignment.Center)
                    .padding(
                        top = 40.dp,
                        start = 50.dp,
                        end = 40.dp
                    )
                    .fillMaxWidth(),
                painter = painterResource(R.drawable.haetae_mypage),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )
            Column(
                modifier = modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
            ) {
                PastPortTopBar(
                    text = stringResource(R.string.mypage)
                )
                UserInfo(
                    modifier = modifier
                        .padding(
                            start = 20.dp,
                            end = 20.dp
                        )
                        .fillMaxWidth(),
                    username = username,
                    logoutClick = {
                        navToAuth()
                    }
                )
                MyPageOption(
                    modifier = modifier
                        .padding(
                            start = 20.dp,
                            end = 20.dp
                        )
                        .fillMaxWidth(),
                    myMedalClick = navToMedal,
                    myReportClick = navToReport,
                    languageClick = navToLanguage
                )
            }
        }
    }
}

@Composable
private fun UserInfo(
    modifier: Modifier = Modifier,
    username: String,
    logoutClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(
                top = 10.dp,
                bottom = 24.dp
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                modifier = modifier
                    .size(74.dp),
                painter = painterResource(R.drawable.default_profile),
                contentDescription = "profile Image"
            )
            Text(
                text = username,
                style = PastPortFontStyle.bold24
            )
        }
        Icon(
            modifier = modifier
                .size(26.dp)
                .noRippleClickable {
                    logoutClick()
                },
            painter = painterResource(R.drawable.icon_logout),
            tint = Red,
            contentDescription = "logout icon"
        )
    }
}

@Composable
fun MyPageOptionItem(
    modifier: Modifier = Modifier,
    icon: Int,
    textResource: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable {
                onClick()
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                modifier = modifier.size(30.dp),
                painter = painterResource(icon),
                contentDescription = null,
                tint = Main
            )
            Text(
                text = stringResource(textResource),
                style = PastPortFontStyle.medium20
            )
        }
        Icon(
            modifier = modifier
                .size(28.dp)
                .rotate(180f),
            painter = painterResource(R.drawable.icon_back_arrow),
            contentDescription = null,
            tint = Gray600
        )
    }
}

@Composable
fun MyPageOption(
    modifier: Modifier = Modifier,
    myMedalClick: () -> Unit,
    myReportClick: () -> Unit,
    languageClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        MyPageOptionItem(
            icon = R.drawable.icon_medal,
            textResource = R.string.mypage_my_medal,
            onClick = myMedalClick
        )
        MyPageOptionItem(
            icon = R.drawable.icon_report,
            textResource = R.string.mypage_my_report,
            onClick = myReportClick
        )
        MyPageOptionItem(
            icon = R.drawable.icon_language,
            textResource = R.string.mypage_language,
            onClick = languageClick
        )
    }
}