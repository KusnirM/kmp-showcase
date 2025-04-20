package mk.digital.kmpsample.ui.components.buttons

import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mk.digital.kmpsample.ui.components.text.button.TextButtonPrimary

@Composable
fun AppTextButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        content = {
            TextButtonPrimary(text)
        }
    )
}