package mk.digital.kmpshowcase.presentation.screen.platformapis

import kotlinx.coroutines.Job
import mk.digital.kmpshowcase.data.biometric.BiometricResult
import mk.digital.kmpshowcase.data.flashlight.FlashlightClient
import mk.digital.kmpshowcase.domain.model.Location
import mk.digital.kmpshowcase.domain.repository.BiometricRepository
import mk.digital.kmpshowcase.domain.repository.LocationRepository
import mk.digital.kmpshowcase.presentation.base.BaseViewModel
import mk.digital.kmpshowcase.presentation.base.NavEvent

class PlatformApisViewModel(
    private val locationRepository: LocationRepository,
    private val biometricRepository: BiometricRepository,
    private val flashlightClient: FlashlightClient,
) : BaseViewModel<PlatformApisUiState>(PlatformApisUiState()) {

    private var locationUpdatesJob: Job? = null

    override fun loadInitialData() {
        newState {
            it.copy(
                biometricsAvailable = biometricRepository.enabled(),
                flashlightAvailable = flashlightClient.isAvailable()
            )
        }
    }

    fun toggleFlashlight() {
        val success = flashlightClient.toggle()
        if (success) {
            newState { it.copy(flashlightOn = !it.flashlightOn) }
        }
    }

    fun share(text: String) {
        navigate(PlatformApisNavEvent.Share(text))
    }

    fun dial() {
        navigate(PlatformApisNavEvent.Dial(DEMO_PHONE_NUMBER))
    }

    fun openLink() {
        navigate(PlatformApisNavEvent.OpenLink(DEMO_URL))
    }

    fun sendEmail(subject: String, body: String) {
        navigate(PlatformApisNavEvent.SendEmail(DEMO_EMAIL, subject, body))
    }

    fun copyToClipboard(text: String) {
        navigate(PlatformApisNavEvent.CopyToClipboard(text))
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

    override fun onResumed() {
        super.onResumed()
        requireState { state -> if (state.shouldTrackLocation) startLocationUpdates() }
    }

    override fun onPaused() {
        super.onPaused()
        requireState { currentState -> newState { it.copy(shouldTrackLocation = currentState.isTrackingLocation) } }
        stopLocationUpdates()
    }

    fun startLocationUpdates() {
        if (locationUpdatesJob?.isActive == true) return
        newState { it.copy(isTrackingLocation = true, locationUpdatesError = false) }
        locationUpdatesJob = observe(
            flow = locationRepository.locationUpdates(highAccuracy = true),
            onEach = { location -> newState { it.copy(trackedLocation = location) } },
            onError = { newState { it.copy(isTrackingLocation = false, locationUpdatesError = true) } }
        )
    }

    fun stopLocationUpdates() {
        locationUpdatesJob?.cancel()
        locationUpdatesJob = null
        newState { it.copy(isTrackingLocation = false) }
    }

    fun authenticateWithBiometrics() {
        execute(
            action = { biometricRepository.authenticate() },
            onLoading = { newState { it.copy(biometricsLoading = true, biometricsResult = null) } },
            onSuccess = { result -> newState { it.copy(biometricsLoading = false, biometricsResult = result) } },
            onError = { error ->
                val result = BiometricResult.SystemError(error.message.orEmpty())
                newState { it.copy(biometricsLoading = false, biometricsResult = result) }
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        stopLocationUpdates()
        if (state.value.flashlightOn) {
            flashlightClient.turnOff()
        }
    }

    private companion object {
        private const val DEMO_PHONE_NUMBER = "+1234567890"
        private const val DEMO_URL = "https://github.com/KusnirM"
        private const val DEMO_EMAIL = "example@example.com"
    }
}

data class PlatformApisUiState(
    val copiedToClipboard: Boolean = false,
    val location: Location? = null,
    val locationLoading: Boolean = false,
    val locationError: Boolean = false,
    val isTrackingLocation: Boolean = false,
    val shouldTrackLocation: Boolean = false,
    val trackedLocation: Location? = null,
    val locationUpdatesError: Boolean = false,
    val biometricsAvailable: Boolean = false,
    val biometricsLoading: Boolean = false,
    val biometricsResult: BiometricResult? = null,
    val flashlightAvailable: Boolean = false,
    val flashlightOn: Boolean = false,
)

sealed interface PlatformApisNavEvent : NavEvent {
    data class Share(val text: String) : PlatformApisNavEvent
    data class Dial(val number: String) : PlatformApisNavEvent
    data class OpenLink(val url: String) : PlatformApisNavEvent
    data class SendEmail(val to: String, val subject: String, val body: String) : PlatformApisNavEvent
    data class CopyToClipboard(val text: String) : PlatformApisNavEvent
}
