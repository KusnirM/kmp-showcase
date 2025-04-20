package mk.digital.kmpsample

import androidx.compose.ui.window.ComposeUIViewController
import mk.digital.kmpsample.ui.components.ShowCase
import platform.UIKit.UIViewController

actual fun getPlatformName(): String = "iOS"

fun MainViewController(): UIViewController = ComposeUIViewController { App() }

fun ShowcaseViewController(): UIViewController = ComposeUIViewController { ShowCase() }
