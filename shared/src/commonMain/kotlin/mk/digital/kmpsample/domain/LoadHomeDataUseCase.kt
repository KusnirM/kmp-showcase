package mk.digital.kmpsample.domain

import mk.digital.kmpsample.domain.useCases.None
import mk.digital.kmpsample.domain.useCases.UseCase
import mk.digital.kmpsample.getPlatformName

class LoadHomeDataUseCase : UseCase<None, String>() {
    override suspend fun run(params: None): String = "Welcome from ${getPlatformName()}"
}
