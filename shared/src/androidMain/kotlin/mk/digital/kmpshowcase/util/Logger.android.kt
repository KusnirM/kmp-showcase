package mk.digital.kmpshowcase.util

import android.util.Log
import mk.digital.kmpshowcase.data.analytics.AnalyticsClient

actual class Logger actual constructor(
    private val analyticsClient: AnalyticsClient
) {

    actual fun e(log: String) {
        Log.e(TAG, log)
    }

    actual fun e(e: Throwable) {
        Log.e(TAG, e.stackTraceToString())
        analyticsClient.recordException(e)
    }

    actual fun e(log: String, e: Throwable) {
        Log.e(TAG, log, e)
        analyticsClient.recordException(e)
    }

    actual fun d(log: String) {
        Log.d(TAG, log)
    }

    companion object {
        private const val TAG = "Logger"
    }
}
