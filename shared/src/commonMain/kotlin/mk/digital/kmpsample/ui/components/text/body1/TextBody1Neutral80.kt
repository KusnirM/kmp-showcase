package mk.digital.kmpsample.ui.components.text.body1

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import mk.digital.kmpsample.ui.foundation.appColors


@Composable
fun TextBody1Neutral80(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
) {
    TextBody1(
        text = text,
        modifier = modifier,
        color = MaterialTheme.appColors.neutral80,
        textAlign = textAlign,
    )
}

@Composable
fun TextBody1Neutral80Medium(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
) {
    TextBody1(
        text = text,
        modifier = modifier,
        color = MaterialTheme.appColors.neutral80,
        textAlign = textAlign,
        fontWeight = FontWeight.Medium
    )
}
