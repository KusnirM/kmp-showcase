package mk.digital.kmpsample.presentation.component.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import mk.digital.kmpsample.presentation.component.text.labelLarge.TextButtonPrimary
import mk.digital.kmpsample.presentation.foundation.appColors
import mk.digital.kmpsample.presentation.foundation.cardCornerRadius6
import mk.digital.kmpsample.presentation.foundation.space4

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
