package mk.digital.kmpshowcase.data.repository

import kotlinx.coroutines.flow.Flow
import mk.digital.kmpshowcase.data.location.LocationClient
import mk.digital.kmpshowcase.domain.exceptions.base.LocationErrorCode
import mk.digital.kmpshowcase.domain.exceptions.base.LocationException
import mk.digital.kmpshowcase.domain.model.Location
import mk.digital.kmpshowcase.domain.repository.LocationRepository

class LocationRepositoryImpl(
    private val locationClient: LocationClient
) : LocationRepository {

    override suspend fun lastKnownLocation(): Location {
        return locationClient.lastKnown() ?: throw LocationException(
            message = "Last known location not available",
            userMessage = "Location not available. Please enable location services.",
            errorCode = LocationErrorCode.NOT_AVAILABLE
        )
    }

    override fun locationUpdates(highAccuracy: Boolean): Flow<Location> {
        return locationClient.updates(highAccuracy)
    }
}
