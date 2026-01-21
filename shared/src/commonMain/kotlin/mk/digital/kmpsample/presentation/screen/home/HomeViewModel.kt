package mk.digital.kmpsample.presentation.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import mk.digital.kmpsample.domain.model.User
import mk.digital.kmpsample.domain.useCase.LoadHomeDataUseCase
import mk.digital.kmpsample.domain.useCase.TrackButtonClickUseCase
import mk.digital.kmpsample.domain.useCase.base.invoke
import mk.digital.kmpsample.presentation.base.BaseViewModel
import mk.digital.kmpsample.presentation.base.NavEvent


class HomeViewModel(
    private val loadHomeDataUseCase: LoadHomeDataUseCase,
    private val trackButtonClickUseCase: TrackButtonClickUseCase,
) : BaseViewModel<HomeUiState>(HomeUiState()) {

    @Composable
    override fun toolbarTitle(): String = "Home"
    override val navIcon: ImageVector? = null

    override fun loadInitialData() {
        execute(
            onLoading = { newState { it.copy(loading = true) } },
            action = { loadHomeDataUseCase() },
            onSuccess = { result -> newState { it.copy(loading = false, users = result) } },
            onError = { e -> newState { it.copy(loading = false, error = e.userMessage) } }
        )
    }

    fun onUserCard(id: Int) {
        execute(
            action = { trackButtonClickUseCase(id) },
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
    val buttonClicked: Boolean = false,
    val error: String? = null
)

sealed interface HomeNavEvent : NavEvent {
    data class ToDetail(val id: Int) : HomeNavEvent
}
