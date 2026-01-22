package mk.digital.kmpshowcase.presentation.base.router

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

actual class SettingsRouterImpl(private val context: Context) : SettingsRouter {
    actual override fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", context.packageName, null)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }
}
