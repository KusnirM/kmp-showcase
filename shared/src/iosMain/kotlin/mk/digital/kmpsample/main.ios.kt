package mk.digital.kmpsample

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AccessibilitySyncOptions
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.PredictiveBackGestureIcon
import com.arkivanov.decompose.extensions.compose.stack.animation.predictiveback.PredictiveBackGestureOverlay
import com.arkivanov.essenty.backhandler.BackDispatcher
import mk.digital.kmpsample.presentation.base.DefaultAppComponent
import mk.digital.kmpsample.presentation.component.ShowCase
import platform.UIKit.UIViewController

actual fun getPlatformName(): String = "iOS"
fun MainViewController(
    component: DefaultAppComponent,
    backDispatcher: BackDispatcher,
): UIViewController = ComposeUIViewController(
    configure = { accessibilitySyncOptions = AccessibilitySyncOptions.Always(null) }
) {
    PredictiveBackGestureOverlay(
        backDispatcher = backDispatcher,
        backIcon = { progress, _ ->
            PredictiveBackGestureIcon(
                imageVector = Icons.Default.ArrowBackIosNew,
                progress = progress,
            )
        },
        modifier = Modifier.fillMaxSize(),
        endEdgeEnabled = false,
    ) {
        MainView(component)
    }
}

fun ShowcaseViewController(): UIViewController = ComposeUIViewController { ShowCase() }
