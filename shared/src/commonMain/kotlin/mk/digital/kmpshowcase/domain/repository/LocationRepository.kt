package mk.digital.kmpshowcase.domain.repository

import mk.digital.kmpshowcase.domain.model.Location

interface LocationRepository {

    suspend fun lastKnownLocation(): Location

}