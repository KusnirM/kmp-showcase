package mk.digital.kmpshowcase.domain.useCase.analytics

import mk.digital.kmpshowcase.data.analytics.AnalyticsClient

class TrackScreenUseCase(
    private val analyticsClient: AnalyticsClient
) {
    operator fun invoke(screenName: String) {
        analyticsClient.trackScreen(screenName)
    }
}
