package com.mk.kmpshowcase.util

import com.mk.kmpshowcase.data.analytics.AnalyticsClient

expect class Logger(analyticsClient: AnalyticsClient) {

    fun e(log: String)

    fun e(e: Throwable)

    fun e(log: String, e: Throwable)

    fun d(log: String)
}
