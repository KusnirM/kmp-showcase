package mk.digital.kmpsample.ui.components.text.button

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import mk.digital.kmpsample.ui.foundation.appColors

@Composable
internal fun TextButton(
    text: String,
    modifier: Modifier = Modifier,
    color: Color,
    textAlign: TextAlign? = null,
) {
    Text(
        modifier = modifier,
        text = text.uppercase(),
        style = MaterialTheme.typography.button,
        color = color,
        fontWeight = FontWeight.Medium,
        textAlign = textAlign
    )
}

@Composable
internal fun TextButtonPrimary(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
) {
    TextButton(
        modifier = modifier,
        text = text.uppercase(),
        color = MaterialTheme.colors.primary,
        textAlign = textAlign
    )
}

@Composable
internal fun TextButtonNeutral0(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
) {
    TextButton(
        modifier = modifier,
        text = text.uppercase(),
        color = MaterialTheme.appColors.neutral0,
        textAlign = textAlign
    )
}