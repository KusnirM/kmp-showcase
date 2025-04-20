package mk.digital.kmpsample.ui.screens.home

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnStop
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mk.digital.kmpsample.domain.LoadHomeDataUseCase
import mk.digital.kmpsample.domain.TrackButtonClickUseCase
import mk.digital.kmpsample.domain.useCases.invoke
import mk.digital.kmpsample.ui.base.coroutineScope

interface HomeComponent {
    val state: StateFlow<HomeUiState>
    fun navigateToDetails(id: String)
}

class HomeComponentImpl(
    componentContext: ComponentContext,
    private val loadHomeDataUseCase: LoadHomeDataUseCase,
    private val trackButtonClickUseCase: TrackButtonClickUseCase,
    private val navigateToDetailsImpl: (String) -> Unit
) : HomeComponent, ComponentContext by componentContext {

    private val scope = coroutineScope()

    private val _state = MutableStateFlow(HomeUiState())
    override val state: StateFlow<HomeUiState> = _state

    init {
        loadInitialData()

        lifecycle.doOnStop {
            println("HomeComponent stopped")
        }
    }

    private fun loadInitialData() {
        scope.launch {
            _state.value = _state.value.copy(title = loadHomeDataUseCase())
        }
    }

    override fun navigateToDetails(id: String) {
        scope.launch {
            trackButtonClickUseCase(id)
            _state.value = _state.value.copy(buttonClicked = true)
            navigateToDetailsImpl(id)
        }
    }
}

data class HomeUiState(
    val loading: Boolean = true,
    val title: String = "",
    val buttonClicked: Boolean = false
)
