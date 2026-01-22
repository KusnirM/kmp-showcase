package agency.yesteam.worker.presentation.component.imagepicker

import agency.yesteam.worker.presentation.component.AppAlertDialog
import agency.yesteam.worker.presentation.component.image.AppIconPrimary
import agency.yesteam.worker.presentation.component.spacers.RowSpacer.Spacer2
import agency.yesteam.worker.presentation.component.text.bodyMedium.TextBodyMediumNeutral100
import agency.yesteam.worker.presentation.foundation.space4
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.PhotoCamera
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import mk.digital.kmpshowcase.presentation.component.imagepicker.PickerAction
import org.jetbrains.compose.resources.stringResource
import yesteam_worker.shared.generated.resources.Res
import yesteam_worker.shared.generated.resources.camera
import yesteam_worker.shared.generated.resources.gallery
import yesteam_worker.shared.generated.resources.imagepicker_title

@Composable
fun ImageSourceOptionDialog(
    title: String = stringResource(Res.string.imagepicker_title),
    onDismissRequest: () -> Unit,
    onAction: (PickerAction) -> Unit,
) {
    AppAlertDialog(
        onDismissRequest = onDismissRequest,
        title = title,
        content = {
            OptionRow(Icons.Outlined.PhotoCamera, stringResource(Res.string.camera)) {
                onAction(PickerAction.Camera)
            }
            OptionRow(Icons.Outlined.Image, stringResource(Res.string.gallery)) {
                onAction(PickerAction.Gallery)
            }
        }
    )
}

@Composable
private fun OptionRow(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .minimumInteractiveComponentSize()
            .clickable(role = Role.Button, onClick = onClick)
            .padding(space4),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppIconPrimary(icon)
        Spacer2()
        TextBodyMediumNeutral100(text = label)
    }
}
