package mk.digital.kmpsample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import mk.digital.kmpsample.ui.screens.Screen
import mk.digital.kmpsample.ui.screens.detail.DetailScreen
import mk.digital.kmpsample.ui.screens.home.HomeScreen
import mk.digital.kmpsample.ui.foundation.AppTheme

@Composable
fun App() {
    AppTheme {
        var screen by remember { mutableStateOf<Screen>(Screen.Home) }

        when (screen) {
            is Screen.Home -> HomeScreen(
                onNavigateToDetail = { screen = Screen.Detail }
            )
            is Screen.Detail -> DetailScreen(
                onBack = { screen = Screen.Home }
            )
        }
    }

}

expect fun getPlatformName(): String