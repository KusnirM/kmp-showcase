package mk.digital.kmpsample.presentation.screen.home

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.StateFlow
import mk.digital.kmpsample.domain.model.User
import mk.digital.kmpsample.domain.useCase.LoadHomeDataUseCase
import mk.digital.kmpsample.domain.useCase.TrackButtonClickUseCase
import mk.digital.kmpsample.domain.useCase.base.invoke
import mk.digital.kmpsample.presentation.base.BaseComponentContext
import mk.digital.kmpsample.presentation.base.UseCaseRunner

interface HomeComponent {
    val state: StateFlow<HomeUiState>
    fun navigateToDetails(id: Int)
}

class HomeComponentImpl(
    componentContext: ComponentContext,
    private val navigateToDetailsImpl: (Int) -> Unit,
    private val loadHomeDataUseCase: LoadHomeDataUseCase,
    private val trackButtonClickUseCase: TrackButtonClickUseCase,
    private val useCaseRunner: UseCaseRunner,
) : HomeComponent, BaseComponentContext<HomeUiState>(componentContext, HomeUiState()) {

    override fun loadInitialData() {
        useCaseRunner(
            job = scope,
            preAction = {
                newState { it.copy(loading = true) }
            },
            action = { loadHomeDataUseCase() },
            onSuccess = { result ->
                newState { it.copy(loading = false, users = result) }
            },
            onError = {
                newState { it.copy(loading = false) }
            }
        )
    }

    override fun navigateToDetails(id: Int) {
        useCaseRunner(
            job = scope,
            action = {
                trackButtonClickUseCase(id)

            },
            onSuccess = {
                newState { it.copy(buttonClicked = true) }
                navigateToDetailsImpl(id)
            }
        )
    }
}

data class HomeUiState(
    val loading: Boolean = true,
    val users: List<User> = emptyList(),
    val buttonClicked: Boolean = false
)

