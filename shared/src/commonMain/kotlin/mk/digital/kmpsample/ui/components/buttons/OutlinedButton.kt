package mk.digital.kmpsample.ui.components.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import mk.digital.kmpsample.ui.components.text.button.TextButtonPrimary
import mk.digital.kmpsample.ui.foundation.appColors
import mk.digital.kmpsample.ui.foundation.cardCornerRadius6
import mk.digital.kmpsample.ui.foundation.space4

private val outlineButtonBorderSize: Dp = 1.dp


@Composable
fun OutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        border = BorderStroke(
            width = outlineButtonBorderSize,
            color = MaterialTheme.appColors.primary,
        ),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = LocalContentColor.current,
        ),
        contentPadding = PaddingValues(space4),
        shape = RoundedCornerShape(cardCornerRadius6),
        content = {
            TextButtonPrimary(text = text.uppercase())
        },
    )
}
