package com.hackaton.pastport.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hackaton.pastport.main.home.HomeScreen
import com.hackaton.pastport.main.map.MapScreen
import com.hackaton.pastport.main.mymedal.MyMedalScreen
import com.hackaton.pastport.main.mypage.MyPageScreen
import com.hackaton.pastport.main.myreport.MyReportScreen
import com.hackaton.pastport.main.qr.QrScanScreen
import com.hackaton.pastport.main.qr.StoryWebViewScreen
import com.hackaton.pastport.main.qr.WriteReportScreen

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
                    navController.navigate(NavigationRoutes.MY_REPORT)                },
                navToMyMedal = {
                    navController.navigate(NavigationRoutes.MY_MEDAL)
                }
            )
        }
        composable(NavigationRoutes.MAP) {
            MapScreen()
        }
        composable(NavigationRoutes.QR) {
            QrScanScreen(
                navToStory = {
                    navController.navigate(NavigationRoutes.STORY)
                }
            )
        }
        composable(NavigationRoutes.MY_PAGE) {
            MyPageScreen(
                navToAuth = navToAuth,
                navToMedal = {
                    navController.navigate(NavigationRoutes.MY_MEDAL)
                },
                navToReport = {
                    navController.navigate(NavigationRoutes.MY_REPORT)
                },
                navToLanguage = {}
            )
        }

        composable(NavigationRoutes.MY_MEDAL) {
            MyMedalScreen(
                navToBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(NavigationRoutes.MY_REPORT) {
            MyReportScreen(
                navToBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(NavigationRoutes.STORY) {
            StoryWebViewScreen(
                navToWrite = {
                    navController.navigate(NavigationRoutes.WRITE_REPORT)
                },
                navToBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(NavigationRoutes.WRITE_REPORT) {
            WriteReportScreen(
                navToBack = {
                    navController.popBackStack()
                },
                navToMain = {
                    navController.navigate(NavigationRoutes.HOME) {
                        popUpTo(NavigationRoutes.HOME) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}