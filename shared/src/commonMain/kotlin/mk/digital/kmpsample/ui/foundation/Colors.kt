package mk.digital.kmpsample.ui.foundation

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val Neutral0Light: Color = Color(0xFFFFFFFF)
internal val Neutral80Light: Color = Color(0xFF232323)
internal val Neutral100Light: Color = Color.Black// possibly80

internal val PrimaryLight: Color = Color(0xFF6200EE)
internal val PrimaryVariantLight: Color = Color(0xFF3700B3)
internal val SecondaryLight: Color = Color(0xFF03DAC6)
internal val SecondaryVariantLight: Color = Color(0xFF018786)
internal val BackgroundLight: Color = Neutral0Light
internal val SurfaceLight: Color = Neutral0Light
internal val ErrorLight: Color = Color.Red
internal val Transparent: Color = Color(0x00)


data class AppColors(
    val material: Colors,
    val neutral0: Color,
    val neutral80: Color,
    val neutral100: Color,
    val transparent: Color = Transparent,
) {
    val primary: Color get() = material.primary
    val primaryVariant: Color get() = material.primaryVariant
    val secondary: Color get() = material.secondary
    val secondaryVariant: Color get() = material.secondaryVariant
    val background: Color get() = material.background
    val surface: Color get() = material.surface
    val error: Color get() = material.error
    val onPrimary: Color get() = material.onPrimary
    val onSecondary: Color get() = material.onSecondary
    val onBackground: Color get() = material.onBackground
    val onSurface: Color get() = material.onSurface
    val onError: Color get() = material.onError
}
