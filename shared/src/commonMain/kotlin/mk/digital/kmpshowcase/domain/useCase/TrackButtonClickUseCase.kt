package mk.digital.kmpshowcase.domain.useCase

import mk.digital.kmpshowcase.data.analytics.AnalyticsClient
import mk.digital.kmpshowcase.domain.useCase.base.UseCase

class TrackButtonClickUseCase(
    private val analyticsClient: AnalyticsClient
) : UseCase<Int, Unit>() {
    override suspend fun run(params: Int) {
        analyticsClient.log("Button Clicked: $params")
    }
}
