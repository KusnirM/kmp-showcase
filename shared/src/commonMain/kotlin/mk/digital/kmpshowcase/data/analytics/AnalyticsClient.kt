package mk.digital.kmpshowcase.data.analytics

interface AnalyticsClient {
    fun trackScreen(screenName: String)
}
