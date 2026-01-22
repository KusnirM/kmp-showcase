package mk.digital.kmpshowcase

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

@Suppress("FunctionNaming")
fun MainViewController(
    onOpenSettings: () -> Unit
): UIViewController = ComposeUIViewController {
    MainView(onOpenSettings = onOpenSettings)
}
