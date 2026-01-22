package mk.digital.kmpshowcase.presentation.component.galery

import agency.yesteam.worker.presentation.component.imagepicker.ImageResult
import android.content.ContentResolver
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import mk.digital.kmpshowcase.util.BitmapUtils

//todo issue with big bitmaps -> crash
@Composable
actual fun rememberGalleryManager(onResult: (ImageResult?) -> Unit): GalleryManager {
    val context = LocalContext.current
    val contentResolver: ContentResolver = context.contentResolver
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
            uri?.let {
                val byteArray = BitmapUtils.getByteArray(uri, contentResolver)
                val imgBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size).asImageBitmap()
                val result = ImageResult(byteArray, imgBitmap)
                onResult.invoke(result)
            }
        }
    return remember {
        GalleryManager(onLaunch = {
            galleryLauncher.launch(
                PickVisualMediaRequest(
                    mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                )
            )
        })
    }
}

actual class GalleryManager actual constructor(private val onLaunch: () -> Unit) {
    actual fun launch() {
        onLaunch()
    }
}