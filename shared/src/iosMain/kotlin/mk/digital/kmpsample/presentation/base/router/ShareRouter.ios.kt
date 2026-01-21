package mk.digital.kmpsample.presentation.base.router

import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication
import platform.UIKit.popoverPresentationController

actual class ShareRouterImpl : ShareRouter {

    actual override fun share(text: String) {
        val activityItems = listOf(text)
        val activityViewController = UIActivityViewController(
            activityItems = activityItems,
            applicationActivities = null
        )

        val rootViewController = UIApplication.sharedApplication.keyWindow?.rootViewController
        rootViewController?.let { vc ->
            try {
                val popover = vc.popoverPresentationController
                popover?.let {
                    it.sourceView = vc.view
                    it.sourceRect = vc.view.bounds
                }
            } catch (e: Exception) {
            }

            vc.presentViewController(
                activityViewController,
                animated = true,
                completion = null
            )
        }
    }
}
