package mk.digital.kmpsample.ui.screens.detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DetailViewModel : ViewModel() {
    private val _state = MutableStateFlow("Detailed Message")
    val state: StateFlow<String> = _state
}