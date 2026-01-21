package mk.digital.kmpsample.presentation.base.insets

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Screen insets - status bar and navigation bar padding.
 * Screens decide how to apply these.
 */
data class ScreenInsets(
    val top: Dp = 0.dp,
    val bottom: Dp = 0.dp,
)

/**
 * Calculate and remember screen insets from system bars.
 */
@Composable
fun rememberScreenInsets(): ScreenInsets {
    return ScreenInsets(
        top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding(),
        bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    )
}