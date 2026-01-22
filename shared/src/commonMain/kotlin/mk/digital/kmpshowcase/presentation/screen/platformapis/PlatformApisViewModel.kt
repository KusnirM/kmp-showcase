package mk.digital.kmpshowcase.presentation.screen.platformapis

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import mk.digital.kmpshowcase.domain.model.Location
import mk.digital.kmpshowcase.domain.repository.LocationRepository
import mk.digital.kmpshowcase.presentation.base.BaseViewModel
import mk.digital.kmpshowcase.presentation.base.router.ExternalRouter

class PlatformApisViewModel(
    private val externalRouter: ExternalRouter,
    private val locationRepository: LocationRepository
) : BaseViewModel<PlatformApisUiState>(PlatformApisUiState()) {

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
        newState { it.copy(locationLoading = true, locationError = false) }
        viewModelScope.launch {
            try {
                val location = locationRepository.lastKnownLocation()
                newState { it.copy(location = location, locationLoading = false) }
            } catch (e: Exception) {
                newState { it.copy(locationLoading = false, locationError = true) }
            }
        }
    }
}

data class PlatformApisUiState(
    val copiedToClipboard: Boolean = false,
    val location: Location? = null,
    val locationLoading: Boolean = false,
    val locationError: Boolean = false
)
