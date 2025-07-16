package com.hackaton.pastport.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hackaton.pastport.main.home.HomeScreen
import com.hackaton.pastport.main.mypage.MyPageScreen

@Composable
fun MainNavigation(
    navController: NavHostController,
    navToAuth: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.HOME
    ) {
        composable(NavigationRoutes.HOME) {
            HomeScreen(
                navToMyReports = {
                    // 내 소감 화면으로 이동
                },
                navToMyMedal = {
                    // 내 메달 화면으로 이동
                }
            )
        }
        composable(NavigationRoutes.MAP) {
            // 지도 화면
        }
        composable(NavigationRoutes.QR) {
            // QR 화면
        }
        composable(NavigationRoutes.MY_PAGE) {
            MyPageScreen(
                navToAuth = navToAuth,
                navToMedal = {
                    // 내 메달 화면
                },
                navToReport = {
                    // 내 소감 화면으로 이동
                },
                navToLanguage = {}
            )
        }
    }
}