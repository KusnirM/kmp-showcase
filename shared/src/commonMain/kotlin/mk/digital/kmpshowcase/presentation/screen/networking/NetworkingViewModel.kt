package mk.digital.kmpshowcase.presentation.screen.networking

import mk.digital.kmpshowcase.domain.model.User
import mk.digital.kmpshowcase.domain.useCase.GetUsersUseCase
import mk.digital.kmpshowcase.domain.useCase.base.invoke
import mk.digital.kmpshowcase.presentation.base.BaseViewModel

class NetworkingViewModel(
    private val getUsersUseCase: GetUsersUseCase
) : BaseViewModel<NetworkingUiState>(NetworkingUiState()) {

    override fun loadInitialData() {
        fetchUsers()
    }

    fun fetchUsers() {
        execute(
            action = { getUsersUseCase() },
            onLoading = { newState { it.copy(isLoading = true, error = null) } },
            onSuccess = { users -> newState { it.copy(isLoading = false, users = users) } },
            onError = { error -> newState { it.copy(isLoading = false, error = error.message) } }
        )
    }

    fun refresh() {
        fetchUsers()
    }
}

data class NetworkingUiState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val error: String? = null
)
