package mk.digital.kmpsample.presentation.foundation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val colors = LightColorPalette
    CompositionLocalProvider(LocalColors provides colors) {
        MaterialTheme(
            colors = colors.material,
            typography = Typography,
            shapes = MaterialTheme.shapes,
            content = {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize(),
                    content = content
                )
            }
        )
    }
}

private val LocalColors = staticCompositionLocalOf { LightColorPalette }

val MaterialTheme.appColors: AppColors
    @Composable
    @ReadOnlyComposable
    get() = LocalColors.current

private val LightColorPalette = AppColors(
    material = lightColors(
        primary = PrimaryLight,
        primaryVariant = PrimaryVariantLight,
        secondary = SecondaryLight,
        secondaryVariant = SecondaryVariantLight,
        background = BackgroundLight,
        surface = SurfaceLight,
        error = ErrorLight,

        onPrimary = Neutral0Light,
        onSecondary = Neutral0Light,
        onBackground = Neutral100Light,
        onSurface = Neutral100Light,
        onError = Neutral0Light,
    ),
    neutral0 = Neutral0Light,
    neutral80 = Neutral80Light,
    neutral100 = Neutral100Light,
)