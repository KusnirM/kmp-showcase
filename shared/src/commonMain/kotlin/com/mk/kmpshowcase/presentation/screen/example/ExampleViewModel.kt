package com.mk.kmpshowcase.presentation.screen.example

import com.mk.kmpshowcase.domain.model.Example
import com.mk.kmpshowcase.domain.useCase.base.invoke
import com.mk.kmpshowcase.domain.useCase.example.GetExamplesUseCase
import com.mk.kmpshowcase.presentation.base.BaseViewModel

class ExampleViewModel(
    private val getExamplesUseCase: GetExamplesUseCase,
) : BaseViewModel<ExampleUiState>(ExampleUiState()) {

    override fun loadInitialData() {
        loadExamples()
    }

    fun loadExamples() {
        execute(
            action = { getExamplesUseCase() },
            onLoading = { newState { it.copy(isLoading = true, error = false) } },
            onSuccess = { examples ->
                newState { it.copy(isLoading = false, examples = examples) }
            },
            onError = {
                newState { it.copy(isLoading = false, error = true) }
            }
        )
    }
}

data class ExampleUiState(
    val examples: List<Example> = emptyList(),
    val isLoading: Boolean = false,
    val error: Boolean = false,
)
