package mk.digital.kmpshowcase.presentation.component.biometric

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mk.digital.kmpshowcase.presentation.component.image.AppIconPrimary
import mk.digital.kmpshowcase.shared.generated.resources.Res
import mk.digital.kmpshowcase.shared.generated.resources.login_biometric_hint
import org.jetbrains.compose.resources.stringResource

@Composable
actual fun BiometricView(
    modifier: Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(64.dp)
    ) {
        AppIconPrimary(
            imageVector = Icons.Filled.Face,
            contentDescription = stringResource(Res.string.login_biometric_hint),
            size = 48.dp,
        )
    }
}
