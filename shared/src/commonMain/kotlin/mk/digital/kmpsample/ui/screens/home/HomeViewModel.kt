package mk.digital.kmpsample.ui.screens.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import mk.digital.kmpsample.domain.GetWelcomeMessageUseCase

class HomeViewModel (
    getWelcomeMessage: GetWelcomeMessageUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(getWelcomeMessage.execute())
    val state: StateFlow<String> = _state
}