package mk.digital.kmpsample.presentation.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import mk.digital.kmpsample.domain.model.User
import mk.digital.kmpsample.domain.useCase.LoadHomeDataUseCase
import mk.digital.kmpsample.domain.useCase.TrackButtonClickUseCase
import mk.digital.kmpsample.domain.useCase.base.invoke
import mk.digital.kmpsample.presentation.base.BaseComponentContext
import mk.digital.kmpsample.presentation.base.NavEvent
import mk.digital.kmpsample.presentation.base.UseCaseRunner


class HomeComponent(
    private val loadHomeDataUseCase: LoadHomeDataUseCase,
    private val trackButtonClickUseCase: TrackButtonClickUseCase,
    private val useCaseRunner: UseCaseRunner,
) : BaseComponentContext<HomeUiState>(HomeUiState()) {

    @Composable
    override fun toolbarTitle(): String = "Home"
    override val navIcon: ImageVector? = null

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

    fun onUserCard(id: Int) {
        useCaseRunner(
            job = scope,
            action = {
                trackButtonClickUseCase(id)

            },
            onSuccess = {
                newState { it.copy(buttonClicked = true) }
                navigate(HomeNavEvent.ToDetail(id))
            }
        )
    }
}

data class HomeUiState(
    val loading: Boolean = true,
    val users: List<User> = emptyList(),
    val buttonClicked: Boolean = false
)

/**
 * Navigation events emitted by HomeComponent.
 * UI layer collects these and performs actual navigation.
 */
sealed interface HomeNavEvent : NavEvent {
    data class ToDetail(val id: Int) : HomeNavEvent
}

