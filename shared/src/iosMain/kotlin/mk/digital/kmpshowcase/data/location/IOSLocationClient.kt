package mk.digital.kmpshowcase.data.location

import kotlinx.cinterop.useContents
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import mk.digital.kmpshowcase.domain.model.Location
import platform.CoreLocation.CLLocation
import platform.CoreLocation.CLLocationManager
import platform.CoreLocation.CLLocationManagerDelegateProtocol
import platform.CoreLocation.kCLLocationAccuracyBest
import platform.CoreLocation.kCLLocationAccuracyHundredMeters
import platform.Foundation.NSError
import platform.Foundation.NSLog
import platform.darwin.NSObject

/**
 * iOS location gateway – starts/stops CoreLocation updates and emits positions.
 * Permission prompts are handled elsewhere (see LocationPermissionManager).
 */
class IOSLocationClient : LocationClient {

    private val manager = CLLocationManager()
    private var out: SendChannel<Location>? = null

    private val delegate = object : NSObject(), CLLocationManagerDelegateProtocol {

        override fun locationManager(manager: CLLocationManager, didUpdateLocations: List<*>) {
            val l = didUpdateLocations.lastOrNull() as? CLLocation ?: run {
                NSLog("[%s] didUpdateLocations: empty or not CLLocation", TAG)
                return
            }
            val (lat, lng) = l.coordinate.useContents { latitude to longitude }
            NSLog("[%s] didUpdateLocations: lat=%f, lng=%f, accuracy=%.2f", TAG, lat, lng, l.horizontalAccuracy)
            out?.trySend(Location(lat, lng))
        }

        override fun locationManager(manager: CLLocationManager, didFailWithError: NSError) {
            NSLog("[%s] didFailWithError: %@", TAG, didFailWithError)
        }
    }

    init {
        manager.delegate = delegate
    }

    override suspend fun lastKnown(): Location? {
        val loc = manager.location
        if (loc == null) {
            NSLog("[%s] lastKnown: null (no cached location yet)", TAG)
            return null
        }
        val (lat, long) = loc.coordinate.useContents { latitude to longitude }
        NSLog("[%s] lastKnown: lat=%f, lng=%f, accuracy=%.2f", TAG, lat, long, loc.horizontalAccuracy)
        return Location(lat, long)
    }

    override fun updates(highAccuracy: Boolean) = callbackFlow {
        out = channel

        manager.desiredAccuracy = if (highAccuracy) kCLLocationAccuracyBest else kCLLocationAccuracyHundredMeters
        manager.startUpdatingLocation()
        awaitClose {
            NSLog("[%s] updates(stop): stopping updates", TAG)
            out = null
            manager.stopUpdatingLocation()
        }
    }

    private companion object {
        private const val TAG = "IOSLocationGateway"
    }
}