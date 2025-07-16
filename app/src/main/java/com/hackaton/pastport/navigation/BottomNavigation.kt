package com.hackaton.pastport.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.hackaton.pastport.R
import com.hackaton.pastport.ui.theme.Gray50
import com.hackaton.pastport.ui.theme.Gray500
import com.hackaton.pastport.ui.theme.Main
import com.hackaton.pastport.ui.theme.PastPortFontStyle
import com.hackaton.pastport.ui.theme.White
import com.hackaton.pastport.ui.utils.noRippleClickable

sealed class BottomNavItem(
    val title: Int,
    val icon: Int,
    val screenRoute: String
) {
    data object Home: BottomNavItem(
        R.string.home,
        R.drawable.icon_home,
        NavigationRoutes.HOME
    )
    data object Map: BottomNavItem(
        R.string.map,
        R.drawable.icon_map,
        NavigationRoutes.MAP
    )
    data object QR: BottomNavItem(
        R.string.qr,
        R.drawable.icon_qr,
        NavigationRoutes.QR
    )
    data object MyPage: BottomNavItem(
        R.string.mypage,
        R.drawable.icon_mypage,
        NavigationRoutes.MY_PAGE
    )
}

private val navItems = listOf<BottomNavItem>(
    BottomNavItem.Home,
    BottomNavItem.Map,
    BottomNavItem.QR,
    BottomNavItem.MyPage
)

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .background(White)
            .padding(
                start = 40.dp,
                end = 40.dp,
                top = 8.dp,
                bottom = 8.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navItems.forEach { navItem ->
            Column {
                Icon(
                    modifier = modifier
                        .size(30.dp)
                        .noRippleClickable {
                            navController.navigate(navItem.screenRoute) {
                                navController.graph.startDestinationRoute?.let {
                                    popUpTo(it) { saveState = true }
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                    painter = painterResource(navItem.icon),
                    tint = if (navItem.screenRoute == currentRoute) Main else Gray500,
                    contentDescription = stringResource(id = navItem.title)
                )
                Text(
                    text = stringResource(navItem.title),
                    style = PastPortFontStyle.semi12,
                    color = if (navItem.screenRoute == currentRoute) Main else Gray500
                )
            }
        }
    }
}