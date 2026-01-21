package mk.digital.kmpsample.presentation.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mk.digital.kmpsample.presentation.component.TopAppBar
import mk.digital.kmpsample.presentation.component.spacers.ColumnSpacer.Spacer4
import mk.digital.kmpsample.presentation.component.text.bodyLarge.TextBodyLargeNeutral80
import mk.digital.kmpsample.presentation.foundation.space4

@Composable
fun ProfileScreen() {
    Column {
        TopAppBar(title = "Profile", backIcon = null)
        Column(Modifier.padding(space4)) {
            Spacer4()
            TextBodyLargeNeutral80("Profile Body")
        }
    }
}
