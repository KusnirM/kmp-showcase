package mk.digital.kmpsample.ui.components.text.h4

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun TextH4Primary(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
) {
    TextH4(
        text = text,
        modifier = modifier,
        color = MaterialTheme.colors.primary,
        textAlign = textAlign
    )
}
