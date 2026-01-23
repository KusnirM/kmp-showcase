package mk.digital.kmpshowcase.presentation.component.biometric

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun BiometricView(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
)
