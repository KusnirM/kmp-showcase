package mk.digital.kmpshowcase.presentation.component.camera

import agency.yesteam.worker.presentation.component.imagepicker.ImageResult
import androidx.compose.runtime.Composable

@Composable
expect fun rememberCameraManager(onResult: (ImageResult?) -> Unit): CameraManager

expect class CameraManager(
    onLaunch: () -> Unit
) {
    fun launch()
}
