package mk.digital.kmpshowcase.presentation.component.imagepicker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mk.digital.kmpshowcase.presentation.component.camera.rememberCameraManager
import mk.digital.kmpshowcase.presentation.component.galery.rememberGalleryManager
import mk.digital.kmpshowcase.presentation.component.permission.PermissionType
import mk.digital.kmpshowcase.presentation.component.permission.PermissionView
import mk.digital.kmpshowcase.presentation.component.permission.galleryRequiresPermission

@Composable
fun ImagePickerView(viewModel: ImagePickerViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val cameraManager = rememberCameraManager { result ->
        viewModel.onImageLoading()
        viewModel.onImageResult(result)
    }

    val galleryManager = rememberGalleryManager { result ->
        viewModel.onImageLoading()
        viewModel.onImageResult(result)
    }

    if (state.showOptionDialog) {
        ImageSourceOptionDialog(
            onDismissRequest = { viewModel.hideDialog() },
            onAction = { viewModel.onActionSelected(it) },
        )
    }

    when (state.action) {
        PickerAction.Camera -> PermissionView(
            permission = PermissionType.CAMERA,
            onDeniedDialogDismiss = { viewModel.resetAction() },
        ) {
            cameraManager.launch()
            viewModel.resetAction()
        }

        PickerAction.Gallery -> {
            if (galleryRequiresPermission) {
                PermissionView(
                    permission = PermissionType.GALLERY,
                    onDeniedDialogDismiss = { viewModel.resetAction() },
                ) {
                    galleryManager.launch()
                    viewModel.resetAction()
                }
            } else {
                LaunchedEffect(state.action) {
                    galleryManager.launch()
                    viewModel.resetAction()
                }
            }
        }

        PickerAction.None -> Unit
    }
}
