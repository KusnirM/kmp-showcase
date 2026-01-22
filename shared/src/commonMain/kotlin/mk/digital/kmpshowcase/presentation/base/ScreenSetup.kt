package mk.digital.kmpshowcase.presentation.base

import androidx.compose.runtime.Composable
import org.koin.compose.viewmodel.koinViewModel

/**
 * Screen helper that combines ViewModel creation and lifecycle handling.
 * Usage:
 * ```
 * entry<HomeSection.Home> {
 *     Screen<HomeViewModel> { viewModel ->
 *         HomeScreen(viewModel)
 *     }
 * }
 * ```
 */
@Composable
inline fun <reified VM : BaseViewModel<*>> WithViewModel(
    content: @Composable (VM) -> Unit
) {
    val viewModel = koinViewModel<VM>()
    (viewModel as? ScreenLifecycle)?.let { ScreenLifecycleEffect(it) }
    content(viewModel)
}
