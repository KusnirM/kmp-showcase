package mk.digital.kmpsample.presentation.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect

/**
 * Callback type for screen configuration changes (toolbar and background).
 */
typealias OnScreenChange = (Toolbar?) -> Unit

/**
 * Wrapper composable that handles lifecycle and screen config callback.
 * Automatically extracts Toolbar and background from viewModel.
 *
 * @param viewModel The component/viewModel for the screen
 * @param onScreenChange Callback to notify parent about toolbar/background changes
 * @param content The screen content
 */
@Composable
fun ScreenWrapper(
    viewModel: BaseComponentContext<*>,
    onScreenChange: OnScreenChange,
    content: @Composable () -> Unit
) {
    // SideEffect runs after successful composition - proper way to sync state
    SideEffect {
        onScreenChange(viewModel as? Toolbar)
    }

    (viewModel as? BaseComponent)?.let { ScreenLifecycleEffect(it) }
    content()
}