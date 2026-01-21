package mk.digital.kmpsample.presentation.component.text.bodyLarge

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import mk.digital.kmpsample.presentation.foundation.appColors


@Composable
fun TextBodyLargeNeutral80(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
) {
    TextBodyLarge(
        text = text,
        modifier = modifier,
        color = MaterialTheme.appColors.neutral80,
        textAlign = textAlign,
    )
}
