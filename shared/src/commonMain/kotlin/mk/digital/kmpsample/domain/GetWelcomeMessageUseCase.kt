package mk.digital.kmpsample.domain

import mk.digital.kmpsample.getPlatformName

class GetWelcomeMessageUseCase {
    fun execute(): String = "Welcome from ${getPlatformName()}"
}