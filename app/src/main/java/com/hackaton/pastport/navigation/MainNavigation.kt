package com.hackaton.pastport.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hackaton.pastport.home.HomeScreen

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
            // 마이페이지 화면
        }
    }
}