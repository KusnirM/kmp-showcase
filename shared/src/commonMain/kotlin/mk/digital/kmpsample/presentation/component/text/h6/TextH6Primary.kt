package mk.digital.kmpsample.presentation.component.text.h6

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TextH6Primary(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
) {
    TextH6(
        text = text,
        modifier = modifier,
        color = MaterialTheme.colors.primary,
        textAlign = textAlign
    )
}
