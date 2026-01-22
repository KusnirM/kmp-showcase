package mk.digital.kmpshowcase.presentation.component.camera

import agency.yesteam.worker.presentation.component.imagepicker.ImageResult
import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider.getUriForFile
import mk.digital.kmpshowcase.util.BitmapUtils
import java.io.File
import java.util.Objects

//todo issue with big bitmaps -> crash
@Composable
actual fun rememberCameraManager(onResult: (ImageResult?) -> Unit): CameraManager {
    val context = LocalContext.current
    val contentResolver: ContentResolver = context.contentResolver
    var imageUri by remember { mutableStateOf(value = Uri.EMPTY) }
    var launchRequested by remember { mutableStateOf(false) }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) {
        if (it && imageUri != null) {
            val byteArray = BitmapUtils.getByteArray(imageUri!!, contentResolver)
            val bitmap = BitmapUtils.getBitmapFromUri(imageUri!!, byteArray, contentResolver)
                ?.asImageBitmap()
            onResult(bitmap?.let { ImageResult(byteArray, bitmap) })
        }
        launchRequested = false
    }

    LaunchedEffect(launchRequested) {
        if (launchRequested && imageUri != null) {
            cameraLauncher.launch(imageUri)
        }
    }

    return remember {
        CameraManager {
            imageUri = getImageUri(context)
            launchRequested = true
        }
    }
}

actual class CameraManager actual constructor(
    private val onLaunch: () -> Unit
) {
    actual fun launch() {
        onLaunch()
    }
}

private fun getImageUri(context: Context): Uri {
    // 1
    val tempFile = File.createTempFile(
        "picture_${System.currentTimeMillis()}", ".png", context.cacheDir
    ).apply { createNewFile() }
    // 2
    val authority = context.applicationContext.packageName + ".provider"
    // 3
    println("getImageUri: ${tempFile.absolutePath}")
    return getUriForFile(
        Objects.requireNonNull(context),
        authority,
        tempFile,
    )
}
