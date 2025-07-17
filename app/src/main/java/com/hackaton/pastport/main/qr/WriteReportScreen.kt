package com.hackaton.pastport.main.qr

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hackaton.pastport.R
import com.hackaton.pastport.main.qr.viewmodel.WriteReportViewModel
import com.hackaton.pastport.ui.PastPortTopBar
import com.hackaton.pastport.ui.theme.Black
import com.hackaton.pastport.ui.theme.Gray700
import com.hackaton.pastport.ui.theme.Main
import com.hackaton.pastport.ui.theme.PastPortFontStyle
import com.hackaton.pastport.ui.theme.White
import com.hackaton.pastport.ui.utils.addFocusCleaner
import com.hackaton.pastport.ui.utils.noRippleClickable

@Composable
fun WriteReportScreen(
    modifier: Modifier = Modifier,
    viewModel: WriteReportViewModel = hiltViewModel(),
    navToBack: () -> Unit,
    navToMain: () -> Unit
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val reviewText = ""
    val isSuccess = viewModel.isSuccess

    if (isSuccess == true) {
        navToMain()
    } else if (isSuccess == false) {
        Toast.makeText(context, "후기 등록 실패", Toast.LENGTH_SHORT).show()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .addFocusCleaner(focusManager)
    ) {
        PastPortTopBar(
            text = "경복궁",
            isBack = true,
            backPress = {
                navToBack()
            }
        )
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.Center)
                    .background(
                        color = Black.copy(alpha = 0.25f)
                    ),
                painter = painterResource(R.drawable.gyeongbok_background),
                contentDescription = null,
                contentScale = ContentScale.FillWidth
            )

            WriteDialog(
                input = reviewText,
                onValueChange = { input ->
                    viewModel.onChangeReviewText(input)
                },
                submitReport = {
                    viewModel.submitReview()
                }
            )
        }
    }
}

@Composable
fun WriteDialog(
    modifier: Modifier = Modifier,
    input: String,
    onValueChange: (String) -> Unit,
    submitReport: () -> Unit
) {
    Column (
        modifier = modifier
            .padding(
                start = 40.dp,
                end = 40.dp
            )
            .fillMaxWidth()
            .height(380.dp)
            .background(
                color = White,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 12.dp,
                bottom = 20.dp
            ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = modifier.fillMaxWidth()
        ) {
            Text(
                modifier = modifier.align(Alignment.Center),
                text = "후기 작성",
                style = PastPortFontStyle.semi20
            )
            Icon(
                modifier = modifier
                    .size(26.dp)
                    .align(Alignment.CenterEnd),
                imageVector = Icons.Filled.Clear,
                contentDescription = null,
                tint = Gray700
            )
        }
        HorizontalDivider(
            modifier = modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = Gray700
        )
        Box(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            BasicTextField(
                modifier = modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(),
                value = input,
                onValueChange = onValueChange,
                textStyle = PastPortFontStyle.medium16,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                decorationBox = { innerTextField ->
                    if (input.isEmpty()) {
                        Text(
                            text = "후기를 작성해주세요!",
                            style = PastPortFontStyle.medium16,
                            color = Gray700
                        )
                    }
                    innerTextField()
                }
            )
            Image(
                modifier = modifier
                    .size(86.dp)
                    .align(Alignment.BottomStart),
                painter = painterResource(R.drawable.medal_gyeongbok),
                contentScale = ContentScale.Fit,
                contentDescription = "medal"
            )
            Text(
                modifier = modifier
                    .align(Alignment.BottomEnd)
                    .wrapContentSize()
                    .background(
                        color = Main,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(
                        start = 12.dp,
                        end = 12.dp,
                        top = 6.dp,
                        bottom = 6.dp
                    )
                    .noRippleClickable {
                        submitReport()
                    },
                text = "작성 완료",
                style = PastPortFontStyle.semi16,
                color = White
            )
        }
    }
}