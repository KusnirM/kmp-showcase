package mk.digital.kmpsample.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mk.digital.kmpsample.presentation.component.buttons.AppTextButton
import mk.digital.kmpsample.presentation.component.text.h4.TextH4Primary

@Composable
fun AppAlertDialog(
    onDismissRequest: () -> Unit,
    title: String? = null,
    message: String,
    buttons: @Composable () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = Modifier.fillMaxWidth(),
        title = title?.let { { TextH4Primary(title) } },
        text = { TextH4Primary(message) },
        buttons = buttons
    )

}

@Composable
fun AppConfirmDialog(
    onDismissRequest: () -> Unit,
    title: String? = null,
    message: String,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = Modifier.fillMaxWidth(),
        title = title?.let { { TextH4Primary(title) } },
        text = { TextH4Primary(message) },
        buttons = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                AppTextButton(text = "Ok", onClick = onDismissRequest)
            }
        }
    )

}
