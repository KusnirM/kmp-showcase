package mk.digital.kmpsample.presentation.component.buttons

import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mk.digital.kmpsample.presentation.component.text.labelLarge.TextButtonPrimary

@Composable
fun AppTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        content = {
            TextButtonPrimary(text)
        }
    )
}
