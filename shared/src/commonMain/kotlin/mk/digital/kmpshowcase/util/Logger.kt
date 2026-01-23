package mk.digital.kmpshowcase.util

import mk.digital.kmpshowcase.data.analytics.AnalyticsClient

expect class Logger(analyticsClient: AnalyticsClient) {

    fun e(log: String)

    fun e(e: Throwable)

    fun e(log: String, e: Throwable)

    fun d(log: String)
}
