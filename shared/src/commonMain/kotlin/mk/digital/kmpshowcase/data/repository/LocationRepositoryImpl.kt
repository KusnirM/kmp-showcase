package mk.digital.kmpshowcase.data.repository

import mk.digital.kmpshowcase.data.location.LocationClient
import mk.digital.kmpshowcase.domain.exceptions.base.ApiException
import mk.digital.kmpshowcase.domain.exceptions.base.DataErrorCode
import mk.digital.kmpshowcase.domain.model.Location
import mk.digital.kmpshowcase.domain.repository.LocationRepository

class LocationRepositoryImpl(
    private val locationClient: LocationClient
) : LocationRepository {


    override suspend fun lastKnownLocation(): Location {
        return locationClient.lastKnown() ?: throw ApiException(1,"")// throw LastKnownLocationException
    }
}