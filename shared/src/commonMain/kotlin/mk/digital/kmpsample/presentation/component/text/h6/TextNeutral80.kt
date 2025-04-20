package mk.digital.kmpsample.presentation.component.text.h6

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import mk.digital.kmpsample.presentation.foundation.appColors

@Composable
fun TextNeutral80(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
) {
    TextH6(
        text = text,
        modifier = modifier,
        color = MaterialTheme.appColors.neutral80,
        textAlign = textAlign
    )
}
