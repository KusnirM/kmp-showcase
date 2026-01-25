package mk.digital.kmpshowcase.domain.useCase.flashlight

import mk.digital.kmpshowcase.domain.repository.FlashlightRepository
import mk.digital.kmpshowcase.domain.useCase.base.None
import mk.digital.kmpshowcase.domain.useCase.base.UseCase

class IsFlashlightAvailableUseCase(
    private val flashlightRepository: FlashlightRepository
) : UseCase<None, Boolean>() {
    override suspend fun run(params: None): Boolean = flashlightRepository.isAvailable()
}
