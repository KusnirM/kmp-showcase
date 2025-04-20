package mk.digital.kmpsample.presentation.component.buttons

import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mk.digital.kmpsample.presentation.component.text.button.TextButtonPrimary

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