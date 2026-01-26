package com.mk.kmpshowcase.presentation.screen.networking

import com.mk.kmpshowcase.domain.model.User
import com.mk.kmpshowcase.domain.useCase.GetUsersUseCase
import com.mk.kmpshowcase.domain.useCase.base.invoke
import com.mk.kmpshowcase.presentation.base.BaseViewModel

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
