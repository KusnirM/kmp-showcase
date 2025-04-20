package mk.digital.kmpsample.util

import platform.Foundation.NSLog

actual object Logger {
    actual fun e(log: String) {
        NSLog("$TAG: $log")
    }

    actual fun e(e: Throwable) {
        NSLog("$TAG ❗️ ${e.message ?: e.toString()}\n${e.stackTraceToString()}")
    }

    actual fun d(log: String) {
        NSLog("$TAG ❗️ $log")
    }

    private const val TAG = "Logger"
}