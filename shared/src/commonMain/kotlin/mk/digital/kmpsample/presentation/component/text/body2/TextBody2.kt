package mk.digital.kmpsample.presentation.component.text.body2

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

@Composable
fun TextBody2(
    text: String,
    modifier: Modifier = Modifier,
    color: Color,
    fontWeight: FontWeight? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    fontStyle: FontStyle? = null,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.body2,
        color = color,
        fontWeight = fontWeight,
        modifier = modifier,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        maxLines = maxLines,
        fontStyle = fontStyle,
    )
}
