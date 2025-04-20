package mk.digital.kmpsample.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mk.digital.kmpsample.ui.components.TopAppBar
import mk.digital.kmpsample.ui.components.spacers.ColumnSpacer.Spacer4
import mk.digital.kmpsample.ui.components.text.body1.TextBody1Neutral80
import mk.digital.kmpsample.ui.foundation.space4

@Composable
fun DetailScreen(component: DetailComponent) {
    Column {
        TopAppBar(title = "Details", backClick = component.onBack)
        Column(Modifier.padding(space4)) {
            Spacer4()
            TextBody1Neutral80("PassedId: ${component.id}")
        }
    }
}