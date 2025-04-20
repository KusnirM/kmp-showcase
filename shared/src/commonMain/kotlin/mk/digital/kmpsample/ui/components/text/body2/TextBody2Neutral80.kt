package mk.digital.kmpsample.ui.components.text.body2

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import mk.digital.kmpsample.ui.foundation.appColors

@Composable
fun TextBody2Neutral80(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
) {
    TextBody2(
        text = text,
        modifier = modifier,
        color = MaterialTheme.appColors.neutral80,
        textAlign = textAlign,
        fontWeight = FontWeight.Normal,
        lineHeight = lineHeight,
    )
}
