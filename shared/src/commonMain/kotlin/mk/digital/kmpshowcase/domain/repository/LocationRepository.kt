package mk.digital.kmpshowcase.domain.repository

import kotlinx.coroutines.flow.Flow
import mk.digital.kmpshowcase.domain.model.Location

interface LocationRepository {
    suspend fun lastKnownLocation(): Location
    fun locationUpdates(highAccuracy: Boolean = false): Flow<Location>
}
