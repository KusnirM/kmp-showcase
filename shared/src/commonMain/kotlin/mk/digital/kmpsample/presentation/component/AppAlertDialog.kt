package mk.digital.kmpsample.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mk.digital.kmpsample.presentation.component.buttons.AppTextButton
import mk.digital.kmpsample.presentation.component.text.headlineMedium.TextHeadlineMediumPrimary

@Composable
fun AppAlertDialog(
    text: String,
    onDismissRequest: () -> Unit,
    title: String? = null,
    dismissButton: @Composable (() -> Unit)? = null,
    confirmButton: @Composable () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        modifier = Modifier.fillMaxWidth(),
        title = title?.let { { TextHeadlineMediumPrimary(title) } },
        text = { TextHeadlineMediumPrimary(text) },
        dismissButton = dismissButton,
        confirmButton = confirmButton
    )
}

@Composable
fun AppConfirmDialog(
    text: String,
    title: String? = null,
    onDismissRequest: () -> Unit,
) {
    AppAlertDialog(
        onDismissRequest = onDismissRequest,
        title = title,
        text = text,
        confirmButton = {
            AppTextButton(text = "Ok", onClick = onDismissRequest)
        }
    )
}
