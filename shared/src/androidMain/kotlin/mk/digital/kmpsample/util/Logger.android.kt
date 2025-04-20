package mk.digital.kmpsample.util

import android.util.Log

actual object Logger {
    actual fun e(log: String) {
        Log.e(TAG, log)
    }

    actual fun e(e: Throwable) {
        Log.e(TAG, e.stackTraceToString())
    }

    actual fun d(log: String) {
        Log.d(TAG, log)
    }

    private const val TAG = "Logger"
}