package mk.digital.kmpshowcase.presentation.screen.feature

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import mk.digital.kmpshowcase.presentation.component.text.headlineMedium.TextHeadlineMediumPrimary

@Composable
fun UiComponentsScreen() {
    PlaceholderScreen("UI Components")
}

@Composable
fun NetworkingScreen() {
    PlaceholderScreen("Networking")
}

@Composable
fun StorageScreen() {
    PlaceholderScreen("Storage")
}

@Composable
fun PlatformApisScreen() {
    PlaceholderScreen("Platform APIs")
}

@Composable
private fun PlaceholderScreen(title: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TextHeadlineMediumPrimary(title)
    }
}
