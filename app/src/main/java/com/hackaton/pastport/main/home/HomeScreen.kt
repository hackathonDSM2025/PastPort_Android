package com.hackaton.pastport.main.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.cheonjaeung.compose.grid.SimpleGridCells
import com.cheonjaeung.compose.grid.VerticalGrid
import com.hackaton.pastport.R
import com.hackaton.pastport.main.home.viewmodel.HomeViewModel
import com.hackaton.pastport.network.model.user.BadgeData
import com.hackaton.pastport.ui.PastPortTopBar
import com.hackaton.pastport.ui.theme.Gray200
import com.hackaton.pastport.ui.theme.Gray50
import com.hackaton.pastport.ui.theme.Gray700
import com.hackaton.pastport.ui.theme.Gray900
import com.hackaton.pastport.ui.theme.Main
import com.hackaton.pastport.ui.theme.PastPortFontStyle
import com.hackaton.pastport.ui.theme.White
import com.hackaton.pastport.ui.utils.noRippleClickable

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navToMyReports: () -> Unit,
    navToMyMedal: () -> Unit
) {
    val totalMedal = 10
    val medalCount = viewModel.medalCount
    val medalList = viewModel.sortedMedalList
    val visitedCount = viewModel.visitedCount
    val reportCount = viewModel.reportCount

    val haeTaeSpeechText = stringResource(R.string.home_haetae_speech)
    val isLoading = viewModel.isLoading

    LaunchedEffect(Unit) {
        viewModel.getInfo()
    }

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
                   .fillMaxSize(),
                painter = painterResource(R.drawable.home_background),
                contentDescription = null,
                contentScale = ContentScale.FillHeight
            )
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                PastPortTopBar(
                    text = stringResource(R.string.home)
                )
                HomeProgress(
                    totalMedal = totalMedal,
                    medalCount = medalCount
                )
                WelcomeCard()
                MyActivity(
                    visitedCount = visitedCount,
                    medalCount = medalCount,
                    reportCount = reportCount,
                    onMedalClick = {
                        navToMyMedal()
                    },
                    onReportClick = {
                        navToMyReports()
                    }
                )
            }
            Row(
                modifier = modifier
                    .align(Alignment.BottomCenter)
                    .padding(
                        bottom = 20.dp
                    )
                    .fillMaxWidth(),
                verticalAlignment = Alignment.Bottom
            ) {
                HaeTaeSpeech(
                    modifier = modifier.fillMaxWidth(0.4f),
                    text = haeTaeSpeechText
                )
                LateMedal(
                    modifier = modifier.fillMaxWidth(),
                    medalList = medalList
                )
            }
        }
    }
}

@Composable
private fun HomeProgress(
    modifier: Modifier = Modifier,
    totalMedal: Int,
    medalCount: Int
) {
    val percent = medalCount.toFloat() / totalMedal

    Row(
        modifier = modifier
            .padding(
                top = 20.dp,
                start = 20.dp,
                end = 20.dp,
                bottom = 10.dp
            )
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.home_degree),
            style = PastPortFontStyle.medium12
        )
        LinearProgressIndicator(
            modifier = modifier
                .fillMaxWidth(0.82f)
                .height(10.dp),
            progress = { percent },
            trackColor = White,
            color = Main,
            strokeCap = StrokeCap.Round
        )
        Text(
            text = "${percent * 100}%",
            style = PastPortFontStyle.medium14,
            color = Gray700
        )
    }
}

@Composable
private fun WelcomeCard(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 12.dp,
                bottom = 10.dp
            )
            .fillMaxWidth()
            .background(
                color = White,
                shape = RoundedCornerShape(4.dp)
            )
            .border(
                width = 1.dp,
                color = Gray50,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(
                start = 16.dp,
                end = 16.dp,
                top = 24.dp,
                bottom = 24.dp
            ),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.home_welcome_title),
            style = PastPortFontStyle.semi16
        )
        Text(
            text = stringResource(R.string.home_welcome_content),
            style = PastPortFontStyle.regular12,
            color = Gray900,
            textAlign = TextAlign.Center,
            lineHeight = 18.sp
        )
    }
}

@Composable
private fun MyActivityItem(
    modifier: Modifier = Modifier,
    icon: Int,
    count: Int,
    unitText: String,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier.noRippleClickable { onClick() },
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            modifier = modifier.size(18.dp),
            painter = painterResource(icon),
            contentDescription = null,
            tint = Main
        )
        Text(
            text = "$count",
            style = PastPortFontStyle.semi12
        )
        Text(
            text = unitText,
            style = PastPortFontStyle.regular12
        )
    }
}

@Composable
private fun MyActivity(
    modifier: Modifier = Modifier,
    visitedCount: Int,
    medalCount: Int,
    reportCount: Int,
    onMedalClick: () -> Unit,
    onReportClick: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(
                start = 40.dp,
                end = 40.dp,
                top = 8.dp,
                bottom = 20.dp
            )
            .fillMaxWidth()
            .background(
                color = White,
                shape = RoundedCornerShape(4.dp)
            )
            .border(
                width = 1.dp,
                color = Gray50,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(
                top = 12.dp,
                bottom = 16.dp,
                start = 26.dp,
                end = 26.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = stringResource(R.string.home_my_activity),
            style = PastPortFontStyle.semi14
        )
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MyActivityItem(
                icon = R.drawable.icon_visited,
                count = visitedCount,
                unitText = stringResource(R.string.home_my_activity_visited),
                onClick = {}
            )
            VerticalDivider(
                modifier = modifier.height(14.dp),
                thickness = 1.dp,
                color = Gray200
            )
            MyActivityItem(
                icon = R.drawable.icon_medal,
                count = medalCount,
                unitText = stringResource(R.string.home_my_activity_medal),
                onClick = onMedalClick
            )
            VerticalDivider(
                modifier = modifier.height(14.dp),
                thickness = 1.dp,
                color = Gray200
            )
            MyActivityItem(
                icon = R.drawable.icon_report,
                count = reportCount,
                unitText = stringResource(R.string.home_my_activity_report),
                onClick = onReportClick
            )
        }
    }
}

@Composable
private fun SpeechBubble(
    modifier: Modifier = Modifier,
    text: String
) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val width = size.width
            val height = size.height
            val cornerRadius = 12.dp.toPx()
            val tailWidth = 20.dp.toPx()
            val tailHeight = 12.dp.toPx()

            val path = Path().apply {
                moveTo(cornerRadius, 0f)
                lineTo(width - cornerRadius, 0f)
                quadraticBezierTo(width, 0f, width, cornerRadius)
                lineTo(width, height - tailHeight - cornerRadius)
                quadraticBezierTo(
                    width,
                    height - tailHeight,
                    width - cornerRadius,
                    height - tailHeight
                )

                lineTo(width / 2 + tailWidth / 2, height - tailHeight)
                lineTo(width / 2f, height)
                lineTo(width / 2 - tailWidth / 2, height - tailHeight)

                lineTo(cornerRadius, height - tailHeight)
                quadraticBezierTo(0f, height - tailHeight, 0f, height - tailHeight - cornerRadius)
                lineTo(0f, cornerRadius)
                quadraticBezierTo(0f, 0f, cornerRadius, 0f)
                close()
            }

            drawPath(
                path = path,
                color = White,
                style = Fill
            )
            drawPath(
                path = path,
                color = Main,
                style = Stroke(width = 1.dp.toPx())
            )
        }

        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(
                    top = 20.dp,
                    bottom = 30.dp,
                    start = 12.dp,
                    end = 12.dp
                )
                .fillMaxWidth(),
            text = text,
            style = PastPortFontStyle.medium12,
            lineHeight = 16.sp
        )
    }
}

@Composable
private fun HaeTaeSpeech(
    modifier: Modifier = Modifier,
    text: String
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        SpeechBubble(
            modifier = Modifier
                .padding(
                    start = 20.dp,
                    end = 16.dp,
                    bottom = 12.dp
                )
                .fillMaxWidth()
                .wrapContentHeight(),
            text = text
        )
        Image(
            modifier = Modifier
                .padding(
                    start = 12.dp
                )
                .fillMaxWidth(),
            painter = painterResource(R.drawable.haetae_home),
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
private fun LateMedal(
    modifier: Modifier = Modifier,
    medalList: List<BadgeData>
) {
    Column(
        modifier = modifier
            .padding(
                start = 4.dp,
                end = 20.dp
            )
            .fillMaxWidth()
            .background(
                color = White.copy(alpha = 0.9f),
                shape = RoundedCornerShape(4.dp)
            )
            .padding(
                top = 10.dp,
                bottom = 10.dp,
                start = 14.dp,
                end = 14.dp
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.home_late_medal),
            style = PastPortFontStyle.semi14
        )
        VerticalGrid(
            modifier = modifier.fillMaxWidth(),
            columns = SimpleGridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            medalList.forEach { medal ->
                AsyncImage(
                    modifier = modifier.size(84.dp),
                    model = medal.imageUrl,
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null
                )
            }
        }
    }
}
