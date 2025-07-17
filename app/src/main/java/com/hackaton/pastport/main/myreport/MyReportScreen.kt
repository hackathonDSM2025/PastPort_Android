package com.hackaton.pastport.main.myreport

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.hackaton.pastport.R
import com.hackaton.pastport.main.myreport.viewmodel.MyReportViewModel
import com.hackaton.pastport.ui.PastPortTopBar
import com.hackaton.pastport.ui.theme.Gray50
import com.hackaton.pastport.ui.theme.Gray500
import com.hackaton.pastport.ui.theme.PastPortFontStyle

@Composable
fun MyReportScreen(
    modifier: Modifier = Modifier,
    viewModel: MyReportViewModel = hiltViewModel(),
    navToBack: () -> Unit
) {
    val reportList = viewModel.reportList
    val isLoading = viewModel.isLoading

    Column (
        modifier = modifier.fillMaxSize()
    ) {
        PastPortTopBar(
            text = stringResource(R.string.mypage_my_report),
            isBack = true,
            backPress = navToBack
        )
        if (isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn (
                modifier = modifier
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        top = 20.dp
                    )
                    .fillMaxWidth()
            ) {
                items(reportList) { item ->
                    ReportListItem(
                        modifier = modifier.fillMaxWidth(),
                        name = item.heritageName,
                        review = item.reviewText,
                        imageUrl = item.heritageImageUrl,
                        createdAt = item.createdAt
                    )
                }
            }
        }
    }
}

@Composable
fun ReportListItem(
    modifier: Modifier = Modifier,
    name: String,
    review: String,
    imageUrl: String,
    createdAt: String
) {
    Row (
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AsyncImage(
            modifier = modifier
                .size(140.dp),
            model = imageUrl,
            contentDescription = name,
            contentScale = ContentScale.FillWidth
        )
        Column (
            modifier = modifier.weight(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = name,
                style = PastPortFontStyle.semi14
            )
            Text(
                modifier = modifier.weight(1f),
                text = review,
                style = PastPortFontStyle.medium12
            )
            Text(
                text = "$createdAt 작성",
                style = PastPortFontStyle.medium12,
                color = Gray500
            )
        }
    }
}