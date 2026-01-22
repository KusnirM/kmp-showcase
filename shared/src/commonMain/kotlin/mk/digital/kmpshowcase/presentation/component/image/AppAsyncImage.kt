package mk.digital.kmpshowcase.presentation.component.image

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.SubcomposeAsyncImage
import mk.digital.kmpshowcase.presentation.component.LoadingView
import org.jetbrains.compose.resources.DrawableResource

@Composable
fun AppAsyncImage(
    imageUrl: String,
    contentDescription: String? = null,
    loading: Boolean = true,
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier = Modifier,
    fallback: (@Composable () -> Unit)? = null,
) {
    SubcomposeAsyncImage(
        model = imageUrl,
        contentDescription = contentDescription,
        contentScale = contentScale,
        loading = if (loading) {
            { LoadingView() }
        } else null,
        modifier = modifier.fillMaxSize(),
        error = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (fallback != null) {
                    fallback()
                } else {
                    AppIcon(imageVector = Icons.Default.BrokenImage)
                }
            }
        },
    )
}


@Composable
fun AppAsyncImage(
    imageUrl: String,
    contentDescription: String? = null,
    loading: Boolean = true,
    modifier: Modifier = Modifier,
    errorPlaceholder: DrawableResource,
) {
    AppAsyncImage(
        imageUrl = imageUrl,
        contentDescription = contentDescription,
        loading = loading,
        modifier = modifier,
        fallback = {
            AppImage(
                resource = errorPlaceholder,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    )
}