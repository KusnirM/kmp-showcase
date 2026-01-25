package mk.digital.kmpshowcase.domain.useCase.location

import mk.digital.kmpshowcase.domain.model.Location
import mk.digital.kmpshowcase.domain.repository.LocationRepository
import mk.digital.kmpshowcase.domain.useCase.base.None
import mk.digital.kmpshowcase.domain.useCase.base.UseCase

class GetLastKnownLocationUseCase(
    private val locationRepository: LocationRepository
) : UseCase<None, Location>() {
    override suspend fun run(params: None): Location = locationRepository.lastKnownLocation()
}
