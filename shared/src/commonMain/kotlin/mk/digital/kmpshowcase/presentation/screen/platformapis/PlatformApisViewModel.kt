package mk.digital.kmpshowcase.presentation.screen.platformapis

import kotlinx.coroutines.Job
import mk.digital.kmpshowcase.domain.model.Location
import mk.digital.kmpshowcase.domain.repository.LocationRepository
import mk.digital.kmpshowcase.presentation.base.BaseViewModel
import mk.digital.kmpshowcase.presentation.base.router.ExternalRouter

class PlatformApisViewModel(
    private val externalRouter: ExternalRouter,
    private val locationRepository: LocationRepository
) : BaseViewModel<PlatformApisUiState>(PlatformApisUiState()) {

    private var locationUpdatesJob: Job? = null

    fun share(text: String) {
        externalRouter.share(text)
    }

    fun dial(phoneNumber: String) {
        externalRouter.dial(phoneNumber)
    }

    fun openLink(url: String) {
        externalRouter.openLink(url)
    }

    fun sendEmail(to: String, subject: String = "", body: String = "") {
        externalRouter.sendEmail(to, subject, body)
    }

    fun copyToClipboard(text: String) {
        externalRouter.copyToClipboard(text)
        newState { it.copy(copiedToClipboard = true) }
    }

    fun resetCopyState() {
        newState { it.copy(copiedToClipboard = false) }
    }

    fun getLocation() {
        execute(
            action = { locationRepository.lastKnownLocation() },
            onLoading = { newState { it.copy(locationLoading = true, locationError = false) } },
            onSuccess = { location ->
                newState { it.copy(location = location, locationLoading = false) }
            },
            onError = {
                newState { it.copy(locationLoading = false, locationError = true) }
            }
        )
    }

    fun startLocationUpdates() {
        if (locationUpdatesJob?.isActive == true) return

        newState { it.copy(isTrackingLocation = true, locationUpdatesError = false) }
        locationUpdatesJob = observe(
            flow = locationRepository.locationUpdates(highAccuracy = true),
            onEach = { location ->
                newState { it.copy(trackedLocation = location) }
            },
            onError = {
                newState { it.copy(isTrackingLocation = false, locationUpdatesError = true) }
            }
        )
    }

    fun stopLocationUpdates() {
        locationUpdatesJob?.cancel()
        locationUpdatesJob = null
        newState { it.copy(isTrackingLocation = false) }
    }

    override fun onCleared() {
        super.onCleared()
        stopLocationUpdates()
    }
}

data class PlatformApisUiState(
    val copiedToClipboard: Boolean = false,
    val location: Location? = null,
    val locationLoading: Boolean = false,
    val locationError: Boolean = false,
    val isTrackingLocation: Boolean = false,
    val trackedLocation: Location? = null,
    val locationUpdatesError: Boolean = false
)
