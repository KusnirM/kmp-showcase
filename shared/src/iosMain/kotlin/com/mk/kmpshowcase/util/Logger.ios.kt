package com.mk.kmpshowcase.util

import com.mk.kmpshowcase.data.analytics.AnalyticsClient
import platform.Foundation.NSLog

actual class Logger actual constructor(private val analyticsClient: AnalyticsClient) {

    actual fun e(log: String) {
        NSLog("$TAG: $log")
    }

    actual fun e(e: Throwable) {
        NSLog("$TAG ❗️ ${e.message ?: e.toString()}\n${e.stackTraceToString()}")
        analyticsClient.recordException(e)
    }

    actual fun e(log: String, e: Throwable) {
        NSLog("$TAG ❗️ $log\n${e.message ?: e.toString()}\n${e.stackTraceToString()}")
        analyticsClient.recordException(e)
    }

    actual fun d(log: String) {
        NSLog("$TAG: $log")
    }

    private companion object {
        private const val TAG = "Logger"
    }
}
