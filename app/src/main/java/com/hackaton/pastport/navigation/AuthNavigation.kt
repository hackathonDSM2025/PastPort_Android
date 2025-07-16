package com.hackaton.pastport.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hackaton.pastport.auth.login.LoginScreen
import com.hackaton.pastport.auth.signup.SignUpScreen

@Composable
fun AuthNavigation(
    navController: NavHostController,
    navToMain: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.SIGN_UP
    ) {
        composable(NavigationRoutes.SIGN_UP) {
            SignUpScreen(
                navToMain = navToMain,
                navToBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(NavigationRoutes.LOGIN) {
            LoginScreen(
                navToMain = navToMain,
                navToSignUp = {
                    navController.navigate(NavigationRoutes.SIGN_UP)
                }
            )
        }
    }
}