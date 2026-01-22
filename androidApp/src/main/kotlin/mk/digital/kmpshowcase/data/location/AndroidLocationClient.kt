package mk.digital.kmpshowcase.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
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

class AndroidLocationClient(ctx: Context) : LocationClient {

    private val client = LocationServices.getFusedLocationProviderClient(ctx)

    //handled outside
    @SuppressLint("MissingPermission")
    override suspend fun lastKnown(): Location? {
        return suspendCancellableCoroutine { cont ->
            client.lastLocation
                .addOnSuccessListener { l ->
                    cont.resume(l?.let { Location(it.latitude, it.longitude) }) { _, _, _ -> }
                }
                .addOnFailureListener { cont.resume(null) { _, _, _ -> } }
        }
    }

    //handled outside
    @SuppressLint("MissingPermission")
    override fun updates(highAccuracy: Boolean): Flow<Location> = callbackFlow {

        val priority = if (highAccuracy) Priority.PRIORITY_HIGH_ACCURACY
        else Priority.PRIORITY_BALANCED_POWER_ACCURACY

        val req = LocationRequest.Builder(priority, /* intervalMillis = */ UPDATE_INTERVAL)
            .setMinUpdateIntervalMillis(MIN_UPDATE_INTERVAL)
            .build()

        val callback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.lastLocation?.let {
                    trySend(Location(it.latitude, it.longitude))
                }
            }
        }
        client.requestLocationUpdates(req, callback, Looper.getMainLooper())
        awaitClose { client.removeLocationUpdates(callback) }
    }

    private companion object {
        private const val UPDATE_INTERVAL = 3_000L
        private const val MIN_UPDATE_INTERVAL = 1_000L
    }
}
