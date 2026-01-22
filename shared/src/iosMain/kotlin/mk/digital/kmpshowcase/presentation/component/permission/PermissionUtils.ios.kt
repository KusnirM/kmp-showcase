package mk.digital.kmpshowcase.presentation.component.permission

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import platform.Foundation.NSNotificationCenter
import platform.Foundation.NSOperationQueue
import platform.UIKit.UIApplicationDidBecomeActiveNotification

@Composable
actual fun RecheckOnResume(onResume: () -> Unit) {
    val latest by rememberUpdatedState(onResume)
    DisposableEffect(Unit) {
        val center = NSNotificationCenter.defaultCenter
        val observer = center.addObserverForName(
            name = UIApplicationDidBecomeActiveNotification,
            `object` = null,
            queue = NSOperationQueue.mainQueue()
        ) { _ -> latest() }
        onDispose { center.removeObserver(observer) }
    }
}

actual val galleryRequiresPermission: Boolean
    get() = true
