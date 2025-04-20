package mk.digital.kmpsample.ui.components.text.h4

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
internal fun TextH4(
    text: String,
    modifier: Modifier = Modifier,
    color: Color,
    textAlign: TextAlign? = null,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.h4,
        color = color,
        modifier = modifier,
        textAlign = textAlign
    )
}
