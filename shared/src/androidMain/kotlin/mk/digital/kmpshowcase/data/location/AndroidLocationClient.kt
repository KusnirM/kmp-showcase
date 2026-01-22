package mk.digital.kmpshowcase.data.location

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import mk.digital.kmpshowcase.domain.model.Location
import mk.digital.kmpshowcase.util.Logger
import kotlin.coroutines.resume

class AndroidLocationClient(context: Context) : LocationClient {

    private val fusedClient = LocationServices.getFusedLocationProviderClient(context)

    private companion object {
        const val LOCATION_UPDATE_INTERVAL_MS = 5000L
        const val LOCATION_MIN_UPDATE_INTERVAL_MS = 2000L
    }

    @SuppressLint("MissingPermission")
    override suspend fun lastKnown(): Location? = suspendCancellableCoroutine { cont ->
        fusedClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    Logger.d("lastKnown: lat=${location.latitude}, lng=${location.longitude}")
                    cont.resume(Location(location.latitude, location.longitude))
                } else {
                    Logger.d("lastKnown: null (no cached location)")
                    cont.resume(null)
                }
            }
            .addOnFailureListener { e ->
                Logger.e("lastKnown failed: ${e.message}", e)
                cont.resume(null)
            }
    }

    @SuppressLint("MissingPermission")
    override fun updates(highAccuracy: Boolean): Flow<Location> = callbackFlow {
        val priority = if (highAccuracy) {
            Priority.PRIORITY_HIGH_ACCURACY
        } else {
            Priority.PRIORITY_BALANCED_POWER_ACCURACY
        }

        val request = LocationRequest.Builder(priority, LOCATION_UPDATE_INTERVAL_MS)
            .setMinUpdateIntervalMillis(LOCATION_MIN_UPDATE_INTERVAL_MS)
            .build()

        val callback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.lastLocation?.let { location ->
                    Logger.d("updates: lat=${location.latitude}, lng=${location.longitude}")
                    trySend(Location(location.latitude, location.longitude))
                }
            }
        }

        Logger.d("updates: starting location updates")
        fusedClient.requestLocationUpdates(request, callback, null)

        awaitClose {
            Logger.d("updates: stopping location updates")
            fusedClient.removeLocationUpdates(callback)
        }
    }
}
