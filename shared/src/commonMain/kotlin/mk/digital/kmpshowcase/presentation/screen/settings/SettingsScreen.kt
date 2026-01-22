package mk.digital.kmpshowcase.presentation.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mk.digital.kmpshowcase.presentation.component.spacers.ColumnSpacer.Spacer4
import mk.digital.kmpshowcase.presentation.component.text.bodyLarge.TextBodyLargeNeutral80
import mk.digital.kmpshowcase.presentation.foundation.space4
import mk.digital.kmpshowcase.shared.generated.resources.Res
import mk.digital.kmpshowcase.shared.generated.resources.screen_settings
import org.jetbrains.compose.resources.stringResource

@Composable
fun SettingsScreen() {
    Column {
        Column(Modifier.padding(space4)) {
            Spacer4()
            TextBodyLargeNeutral80(stringResource(Res.string.screen_settings))
        }
    }
}
