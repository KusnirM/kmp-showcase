package com.mk.kmpshowcase.presentation.screen.example

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mk.kmpshowcase.domain.model.Example
import com.mk.kmpshowcase.presentation.base.lifecycleAwareViewModel
import com.mk.kmpshowcase.presentation.foundation.space2
import com.mk.kmpshowcase.presentation.foundation.space4

@Composable
fun ExampleScreen(
    viewModel: ExampleViewModel = lifecycleAwareViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ExampleScreen(
        state = state,
        onRetry = viewModel::loadExamples,
    )
}

@Composable
fun ExampleScreen(
    state: ExampleUiState,
    onRetry: () -> Unit = {},
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            state.error -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Error loading examples")
                    Button(onClick = onRetry) {
                        Text("Retry")
                    }
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(space4),
                    verticalArrangement = Arrangement.spacedBy(space2)
                ) {
                    items(state.examples) { example ->
                        ExampleItem(example = example)
                    }
                }
            }
        }
    }
}

@Composable
private fun ExampleItem(
    example: Example,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(space4)
        ) {
            Text(
                text = example.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "ID: ${example.id}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
