package com.hackaton.pastport

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hackaton.pastport.navigation.AuthNavigation
import com.hackaton.pastport.navigation.BottomNavigation
import com.hackaton.pastport.navigation.MainNavigation
import com.hackaton.pastport.navigation.NavigationRoutes
import com.hackaton.pastport.ui.theme.PastPortTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PastPortTheme {
                PastPortScreen()
            }
        }
    }
}

private val bottomNav = listOf(
    NavigationRoutes.HOME,
    NavigationRoutes.MAP,
    NavigationRoutes.QR,
    NavigationRoutes.MY_PAGE
)

@Composable
fun PastPortScreen(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    var isAuthenticated by remember { mutableStateOf(false) }

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route

    if (isAuthenticated) {
        Scaffold(
            bottomBar = {
                if (currentDestination in bottomNav) {
                    BottomNavigation(navController = navController)
                }
            }
        ) {
            Box(modifier = modifier.padding(it)) {
                MainNavigation(
                    navController = navController,
                    navToAuth = {
                        isAuthenticated = false
                    }
                )
            }
        }
    } else {
        AuthNavigation(
            navController = navController,
            navToMain = {
                isAuthenticated = true
            }
        )
    }
}