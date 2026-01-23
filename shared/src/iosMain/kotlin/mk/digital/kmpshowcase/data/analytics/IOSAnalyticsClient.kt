package mk.digital.kmpshowcase.data.analytics

class IOSAnalyticsClient : AnalyticsClient {

    override fun trackScreen(screenName: String) {
        screenTrackingHandler?.invoke(screenName)
    }

    companion object {
        var screenTrackingHandler: ((String) -> Unit)? = null
    }
}
