package mk.digital.kmpsample.presentation.component.text.headlineMedium

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
internal fun TextHeadlineMedium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color,
    textAlign: TextAlign? = null,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        color = color,
        modifier = modifier,
        textAlign = textAlign
    )
}
