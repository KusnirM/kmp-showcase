package mk.digital.kmpshowcase.presentation.component.galery

import agency.yesteam.worker.presentation.component.imagepicker.ImageResult
import androidx.compose.runtime.Composable

@Composable
expect fun rememberGalleryManager(onResult: (ImageResult?) -> Unit): GalleryManager

expect class GalleryManager(
    onLaunch: () -> Unit
) {
    fun launch()
}
