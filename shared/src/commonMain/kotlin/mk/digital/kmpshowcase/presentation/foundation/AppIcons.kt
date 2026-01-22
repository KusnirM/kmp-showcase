package mk.digital.kmpshowcase.presentation.foundation

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.addPathNodes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class IconPath(
    val fill: Color,
    val path: String,
)

object AppIcons {

    // Chevrons
    val ChevronUp: ImageVector by lazy {
        buildIcon(
            name = "ChevronUp",
            width = 24.dp,
            height = 24.dp,
            paths = listOf(
                IconPath(Color.Black, "M7.41,15.41L12,10.83l4.59,4.58L18,14l-6,-6l-6,6z")
            )
        )
    }

    val ChevronDown: ImageVector by lazy {
        buildIcon(
            name = "ChevronDown",
            width = 24.dp,
            height = 24.dp,
            paths = listOf(
                IconPath(Color.Black, "M7.41,8.59L12,13.17l4.59,-4.58L18,10l-6,6l-6,-6z")
            )
        )
    }

    // Flags
    val FlagSK: ImageVector by lazy {
        buildIcon(
            name = "FlagSK",
            width = 24.dp,
            height = 16.dp,
            paths = listOf(
                IconPath(Color(0xFFFFFFFF), "M0,0h24v5.33H0z"),
                IconPath(Color(0xFF0B4EA2), "M0,5.33h24v5.33H0z"),
                IconPath(Color(0xFFEE1C25), "M0,10.67h24v5.33H0z"),
            )
        )
    }

    val FlagEN: ImageVector by lazy {
        buildIcon(
            name = "FlagEN",
            width = 24.dp,
            height = 16.dp,
            paths = listOf(
                IconPath(Color(0xFF012169), "M0,0h24v16H0z"),
                IconPath(Color(0xFFFFFFFF), "M0,6h24v4H0z"),
                IconPath(Color(0xFFFFFFFF), "M10,0h4v16h-4z"),
                IconPath(Color(0xFFC8102E), "M0,7h24v2H0z"),
                IconPath(Color(0xFFC8102E), "M11,0h2v16h-2z"),
            )
        )
    }

    // Multi-path icon builder (each path has its own fill color)
    private fun buildIcon(
        name: String,
        width: Dp,
        height: Dp,
        paths: List<IconPath>,
    ): ImageVector = ImageVector.Builder(
        name = name,
        defaultWidth = width,
        defaultHeight = height,
        viewportWidth = width.value,
        viewportHeight = height.value
    ).apply {
        paths.forEach { iconPath ->
            addPath(
                fill = SolidColor(iconPath.fill),
                pathData = addPathNodes(iconPath.path)
            )
        }
    }.build()
}
