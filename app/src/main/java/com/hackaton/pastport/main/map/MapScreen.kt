package com.hackaton.pastport.main.map

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.hackaton.pastport.R
import com.hackaton.pastport.main.map.viewmodel.MapViewModel
import com.hackaton.pastport.ui.PastPortTopBar
import com.hackaton.pastport.ui.theme.Gray100
import com.hackaton.pastport.ui.theme.Gray300
import com.hackaton.pastport.ui.theme.Gray400
import com.hackaton.pastport.ui.theme.Gray600
import com.hackaton.pastport.ui.theme.Main
import com.hackaton.pastport.ui.theme.PastPortFontStyle
import com.hackaton.pastport.ui.theme.White
import com.hackaton.pastport.ui.utils.addFocusCleaner
import com.hackaton.pastport.ui.utils.noRippleClickable
import com.hackaton.pastport.utils.dpFromLatitude
import com.hackaton.pastport.utils.dpFromLongitude
import com.hackaton.pastport.utils.openMapNavigation

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    viewModel: MapViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val keyword = viewModel.keyword
    val latitude = viewModel.latitude
    val longitude = viewModel.longitude
    val imageUrl = viewModel.imageUrl
    val detailText = viewModel.description

    val isLoading = viewModel.isLoading

    var isShowDetail by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .addFocusCleaner(focusManager),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PastPortTopBar(
                text = stringResource(R.string.map)
            )
            SearchBar(
                keyword = keyword,
                onValueChange = { input ->
                    viewModel.onKeywordChange(input)
                },
                onSearch = {
                    if (!isLoading) {
                        viewModel.onSearch()
                    }
                }
            )
            BoxWithConstraints(
                modifier = modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                val mapWidthDp = maxWidth
                val mapHeightDp = maxHeight

                Image(
                    modifier = modifier
                        .align(Alignment.Center)
                        .fillMaxSize(),
                    painter = painterResource(R.drawable.korea_map),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                if (latitude != 0f && longitude != 0f) {
                    val offsetX = dpFromLongitude(longitude, mapWidthDp)
                    val offsetY = dpFromLatitude(latitude, mapHeightDp)

                    Icon(
                        modifier = modifier
                            .size(32.dp)
                            .absoluteOffset(
                                x = offsetX,
                                y = offsetY
                            ),
                        painter = painterResource(
                            when (keyword) {
                                "경복궁" -> R.drawable.medal_gyeongbok
                                "탑골공원" -> R.drawable.medal_tapgol
                                "불국사" -> R.drawable.medal_bulguk
                                "고인돌" -> R.drawable.medal_dolmen
                                "하회마을" -> R.drawable.medal_hahoe
                                "남한산성" -> R.drawable.medal_namhansanseong
                                else -> R.drawable.icon_medal
                            }
                        ),
                        contentDescription = null
                    )
                }
            }
        }
        if (isShowDetail) {
            Dialog(
                onDismissRequest = {
                    isShowDetail = false
                }
            ) {
                DetailDialog(
                    name = keyword,
                    description = detailText,
                    imageUrl = imageUrl,
                    onDismiss = {
                        isShowDetail = false
                    },
                    moveToMap = {
                        openMapNavigation(context, latitude, longitude)
                    }
                )
            }
        }
    }
}

@Composable
private fun SearchBar(
    modifier: Modifier = Modifier,
    keyword: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(
                color = White,
                shape = CircleShape
            )
            .border(
                width = 1.dp,
                color = Gray100,
                shape = CircleShape
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
                .padding(end = 24.dp),
            value = keyword,
            onValueChange = onValueChange,
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch()
                    focusManager.clearFocus(true)
                }
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            textStyle = PastPortFontStyle.medium14,
            cursorBrush = SolidColor(Main),
            decorationBox = { innerTextField ->
                if (keyword.isEmpty()) {
                    Text(
                        text = stringResource(R.string.map_search_hint),
                        style = PastPortFontStyle.medium14,
                        color = Gray300
                    )
                }
                innerTextField()
            }
        )
        Box(
            modifier = modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                modifier = modifier.size(20.dp),
                imageVector = Icons.Outlined.Search,
                tint = Gray400,
                contentDescription = "search icon"
            )
        }
    }
}

@Composable
fun DetailDialog(
    modifier: Modifier = Modifier,
    name: String,
    description: String,
    imageUrl: String,
    onDismiss: () -> Unit,
    moveToMap: () -> Unit
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
            .border(
                width = 1.dp,
                color = Main,
                shape = RoundedCornerShape(14.dp)
            )
            .padding(
                start = 24.dp,
                end = 24.dp,
                top = 16.dp,
                bottom = 16.dp
            ),
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalAlignment = Alignment.End
    ) {
        Row (
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = name,
                style = PastPortFontStyle.semi20
            )
            Icon(
                modifier = modifier
                    .size(28.dp)
                    .noRippleClickable { onDismiss() },
                imageVector = Icons.Filled.Clear,
                tint = Gray600,
                contentDescription = "cancel"
            )
        }
        AsyncImage(
            modifier = modifier.fillMaxWidth(),
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )
        Text(
            modifier = modifier.fillMaxWidth(),
            text = description,
            style = PastPortFontStyle.medium12
        )
        Text(
            modifier = modifier.noRippleClickable { moveToMap() },
            text = stringResource(R.string.map_move_to_map),
            style = PastPortFontStyle.medium12,
            color = Gray400,
            textDecoration = TextDecoration.Underline
        )
    }
}