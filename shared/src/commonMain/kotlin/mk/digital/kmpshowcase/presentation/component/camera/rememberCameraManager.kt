package mk.digital.kmpshowcase.presentation.component.camera

import mk.digital.kmpshowcase.presentation.component.imagepicker.ImageResult
import androidx.compose.runtime.Composable

@Composable
expect fun rememberCameraManager(onResult: (ImageResult?) -> Unit): CameraManager

expect class CameraManager(
    onLaunch: () -> Unit
) {
    fun launch()
}
