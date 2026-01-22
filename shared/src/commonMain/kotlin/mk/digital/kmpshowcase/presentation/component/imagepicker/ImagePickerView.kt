package mk.digital.kmpshowcase.presentation.component.imagepicker


import agency.yesteam.worker.presentation.component.imagepicker.ImageResult
import agency.yesteam.worker.presentation.component.imagepicker.ImageSourceOptionDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
import mk.digital.kmpshowcase.presentation.component.camera.rememberCameraManager
import mk.digital.kmpshowcase.presentation.component.galery.rememberGalleryManager
import mk.digital.kmpshowcase.presentation.component.permission.PermissionType
import mk.digital.kmpshowcase.presentation.component.permission.PermissionView
import mk.digital.kmpshowcase.presentation.component.permission.galleryRequiresPermission

//todo move states into Viewmodels (galery galery viewmodel, cameraviewmodel and just observe a states)
@Composable
fun ImagePickerView(
    onImageChanged: (ImageResult?) -> Unit,
    onImageLoading: () -> Unit = {},
    onOptionDialogChanged: (Boolean) -> Unit,
    showOptionDialog: Boolean = false,
) {
    val scope = rememberCoroutineScope()
    var action by remember { mutableStateOf(PickerAction.None) }
    var pendingAction by remember { mutableStateOf<PickerAction?>(null) }
    var busy by remember { mutableStateOf(false) }

    fun launchOnce(block: () -> Unit) {
        if (busy) {
            action = PickerAction.None
            return
        }
        busy = true
        try {
            block()
        } finally {
            busy = false
            action = PickerAction.None
        }
    }

    val onImageResult: (ImageResult?) -> Unit = {
        onImageLoading()
        scope.launch { onImageChanged(it) }
    }

    val cameraManager = rememberCameraManager(onImageResult)
    val galleryManager = rememberGalleryManager(onImageResult)

    if (showOptionDialog) {
        ImageSourceOptionDialog(
            onDismissRequest = { onOptionDialogChanged(false) },
            onAction = { selected ->
                pendingAction = selected
                onOptionDialogChanged(false)
            },
        )
    }

    LaunchedEffect(showOptionDialog, pendingAction, busy) {
        if (!showOptionDialog && pendingAction != null && !busy) {
            action = pendingAction!!
            pendingAction = null
        }
    }

    when (action) {
        PickerAction.Camera -> PermissionView(
            permission = PermissionType.CAMERA,
            onDeniedDialogDismiss = { action = PickerAction.None },
        ) {
            launchOnce { cameraManager.launch() }
        }

        PickerAction.Gallery -> {
            if (galleryRequiresPermission) {
                PermissionView(
                    permission = PermissionType.GALLERY,
                    onDeniedDialogDismiss = { action = PickerAction.None },
                ) {
                    launchOnce { galleryManager.launch() }
                }
            } else {
                LaunchedEffect(action) {
                    launchOnce { galleryManager.launch() }
                }
            }
        }

        PickerAction.None -> Unit
    }
}