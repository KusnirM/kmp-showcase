package mk.digital.kmpsample.domain

import mk.digital.kmpsample.domain.useCases.UseCase

class TrackButtonClickUseCase : UseCase<String, Unit>() {
    override suspend fun run(params: String) {
        println("Button Clicked: $params")
    }
}