package mk.digital.kmpshowcase.data.analytics

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class AndroidAnalyticsClient(context: Context) : AnalyticsClient {

    private val analytics = FirebaseAnalytics.getInstance(context)

    override fun trackScreen(screenName: String) {
        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW, Bundle().apply {
            putString(FirebaseAnalytics.Param.SCREEN_NAME, screenName)
        })
    }
}
