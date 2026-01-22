package mk.digital.kmpshowcase.presentation.screen.platformapis

import mk.digital.kmpshowcase.presentation.base.BaseViewModel
import mk.digital.kmpshowcase.presentation.base.router.ExternalRouter

class PlatformApisViewModel(
    private val externalRouter: ExternalRouter
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
}

data class PlatformApisUiState(
    val copiedToClipboard: Boolean = false
)
