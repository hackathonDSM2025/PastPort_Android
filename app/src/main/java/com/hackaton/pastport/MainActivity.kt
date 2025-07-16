package com.hackaton.pastport

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
            topBar = {},
            bottomBar = {}
        ) {
            Box(modifier = modifier.padding(it)) {
                // 메인 화면
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