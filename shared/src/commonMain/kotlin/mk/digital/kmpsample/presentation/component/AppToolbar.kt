package mk.digital.kmpsample.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import mk.digital.kmpsample.presentation.component.text.h6.TextNeutral80
import mk.digital.kmpsample.presentation.foundation.appColors
import mk.digital.kmpsample.presentation.foundation.space6


@Composable
fun TopAppBar(
    modifier: Modifier = Modifier,
    title: String? = null,
    backIcon: ImageVector? = Icons.AutoMirrored.Filled.ArrowBack,
    backClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth().statusBarsPadding(),
        title = { title?.let { TextNeutral80(text = title) } },
        navigationIcon = {
            backIcon?.let {
                Icon(
                    modifier = Modifier
                        .size(space6)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = backClick
                        ),
                    imageVector = backIcon,
                    contentDescription = "Back Arrow",
                    tint = MaterialTheme.appColors.neutral80,
                )
            }
        },
        actions = {
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                content = actions
            )
        },
        contentColor = MaterialTheme.appColors.transparent
    )
}
