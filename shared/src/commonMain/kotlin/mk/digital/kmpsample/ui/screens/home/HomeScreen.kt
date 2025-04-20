package mk.digital.kmpsample.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import mk.digital.kmpsample.ui.components.TopAppBar
import mk.digital.kmpsample.ui.components.buttons.ContainedButton
import mk.digital.kmpsample.ui.components.spacers.ColumnSpacer.Spacer4
import mk.digital.kmpsample.ui.components.text.body1.TextBody1Neutral80
import mk.digital.kmpsample.ui.foundation.space4
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    onNavigateToDetail: () -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val message by viewModel.state.collectAsState()
    Column {
        TopAppBar(title = "Home", backIcon = null)
        Column(Modifier.padding(space4)) {
            Spacer4()
            TextBody1Neutral80(message)
            Spacer4()
            ContainedButton(text = "Go to Details", onClick = onNavigateToDetail)
        }
    }
}