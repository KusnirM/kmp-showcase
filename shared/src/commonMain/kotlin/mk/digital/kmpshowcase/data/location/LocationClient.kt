package mk.digital.kmpshowcase.data.location

import kotlinx.coroutines.flow.Flow
import mk.digital.kmpshowcase.domain.model.Location


interface LocationClient {
    suspend fun lastKnown(): Location?
    fun updates(highAccuracy: Boolean = false): Flow<Location>
}
