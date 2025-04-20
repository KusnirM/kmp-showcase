package mk.digital.kmpsample.domain.useCase

import mk.digital.kmpsample.domain.useCase.base.UseCase
import mk.digital.kmpsample.util.Logger

class TrackButtonClickUseCase : UseCase<Int, Unit>() {
    override suspend fun run(params: Int) {
        Logger.d("Button Clicked: $params")
    }
}