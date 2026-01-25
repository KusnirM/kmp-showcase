package mk.digital.kmpshowcase.data.repository

import mk.digital.kmpshowcase.data.flashlight.FlashlightClient
import mk.digital.kmpshowcase.domain.repository.FlashlightRepository

class FlashlightRepositoryImpl(
    private val flashlightClient: FlashlightClient
) : FlashlightRepository {

    override fun isAvailable(): Boolean = flashlightClient.isAvailable()

    override fun turnOn(): Boolean = flashlightClient.turnOn()

    override fun turnOff(): Boolean = flashlightClient.turnOff()

    override fun toggle(): Boolean = flashlightClient.toggle()
}
