package mk.digital.kmpshowcase.util

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import androidx.exifinterface.media.ExifInterface


object BitmapUtils {

    fun getByteArray(
        uri: Uri, contentResolver: ContentResolver
    ): ByteArray {
        val inputStream = contentResolver.openInputStream(uri)
        val bytes =  inputStream?.use { it.readBytes() } ?: ByteArray(0)
        inputStream?.close()
        return bytes
    }

    fun getBitmapFromUri(
        uri: Uri,
        byteArray: ByteArray,
        contentResolver: ContentResolver
    ): Bitmap? {
        try {
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

            // acquiring EXIF orientation
            val exif = ExifInterface(contentResolver.openInputStream(uri)!!)
            val orientation = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )

            val matrix = Matrix()
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> matrix.postRotate(90f)
                ExifInterface.ORIENTATION_ROTATE_180 -> matrix.postRotate(180f)
                ExifInterface.ORIENTATION_ROTATE_270 -> matrix.postRotate(270f)
            }

            return if (orientation == ExifInterface.ORIENTATION_NORMAL) {
                bitmap
            } else {
                Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            println("getBitmapFromUri Exception: ${e.message}")
            println("getBitmapFromUri Exception: ${e.localizedMessage}")
            return null
        }
    }
}