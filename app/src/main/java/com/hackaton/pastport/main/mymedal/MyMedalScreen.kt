package com.hackaton.pastport.main.mymedal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.hackaton.pastport.R
import com.hackaton.pastport.main.mymedal.viewmodel.MyMedalViewModel
import com.hackaton.pastport.network.model.badge.BadgeListItemData
import com.hackaton.pastport.ui.PastPortTopBar
import com.hackaton.pastport.ui.theme.Black
import com.hackaton.pastport.ui.theme.Gray300
import com.hackaton.pastport.ui.theme.Gray500
import com.hackaton.pastport.ui.theme.Gray600
import com.hackaton.pastport.ui.theme.PastPortFontStyle
import com.hackaton.pastport.ui.theme.White
import com.hackaton.pastport.ui.utils.noRippleClickable

@Composable
fun MyMedalScreen(
    modifier: Modifier = Modifier,
    viewModel: MyMedalViewModel = hiltViewModel(),
    navToBack: () -> Unit
) {
    val medalList = viewModel.medalList
    val selectedMedal = viewModel.selectedMedalDetail
    val isLoading = viewModel.isLoading

    var isShowDetail by remember { mutableStateOf(false) }

    if (isShowDetail) {

    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
        ) {
            PastPortTopBar(
                text = stringResource(R.string.mypage_my_medal),
                isBack = true,
                backPress = navToBack
            )
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                LazyColumn(
                    modifier = modifier
                        .padding(
                            start = 20.dp,
                            end = 20.dp,
                            top = 20.dp
                        )
                        .fillMaxWidth()
                ) {
                    items(medalList) { item ->
                        MedalListItem(
                            name = item.heritageName,
                            earnedAt = item.earnedAt,
                            imageUrl = item.imageUrl,
                            onClick = {
                                viewModel.selectedMedalDetail(
                                    medal = item
                                )
                            }
                        )
                    }
                }
            }
        }
        if (isShowDetail) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(Black.copy(0.25f))
            ) {
                BadgeDetailDialog(
                    modifier = modifier.align(Alignment.Center),
                    data = selectedMedal
                )
            }
        }
    }
}

@Composable
fun MedalListItem(
    modifier: Modifier = Modifier,
    name: String,
    earnedAt: String,
    imageUrl: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = modifier.align(Alignment.CenterStart)
        ) {
            AsyncImage(
                modifier = modifier.size(46.dp),
                model = imageUrl,
                contentDescription = null
            )
            Column {
                Text(
                    text = name,
                    style = PastPortFontStyle.semi16
                )
                Text(
                    text = earnedAt,
                    style = PastPortFontStyle.medium16,
                    color = Gray300
                )
            }
        }
        Text(
            modifier = modifier
                .align(Alignment.CenterEnd)
                .noRippleClickable {
                    onClick()
                },
            text = stringResource(R.string.show_detail),
            style = PastPortFontStyle.medium14,
            color = Gray300,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Composable
fun BadgeDetailDialog(
    modifier: Modifier = Modifier,
    data: BadgeListItemData
) {
    Column (
        modifier = modifier
            .padding(
                start = 40.dp,
                end = 40.dp
            )
            .fillMaxWidth()
            .background(
                color = White,
                shape = RoundedCornerShape(14.dp)
            )
            .padding(
                top = 16.dp,
                bottom = 24.dp,
                start = 24.dp,
                end = 24.dp
            ),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Row (
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = data.heritageName,
                style = PastPortFontStyle.semi20
            )
            Text(
                text = data.earnedAt,
                style = PastPortFontStyle.medium16,
                color = Gray500
            )
            Icon(
                imageVector = Icons.Filled.Clear,
                tint = Gray600,
                contentDescription = "cancel"
            )
        }
        Box(
            modifier = modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = data.heritageImageUrl,
                contentDescription = data.heritageName
            )
            AsyncImage(
                modifier = modifier
                    .padding(
                        start = 2.dp,
                        top = 2.dp
                    )
                    .align(Alignment.TopStart)
                    .size(80.dp)
                    .rotate(40f),
                model = data.imageUrl,
                contentDescription = data.heritageName
            )
        }
        Text(
            modifier = modifier
                .fillMaxWidth(),
            text = data.description,
            style = PastPortFontStyle.medium12
        )
    }
}