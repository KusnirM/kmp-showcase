package mk.digital.kmpsample.presentation.component.ext

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

@Composable
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() },
        onClick = onClick
    )
}

@Composable
fun Modifier.loadedVerticalScroll(loading: Boolean, scrollState: ScrollState = rememberScrollState()): Modifier = composed {
    then(if (loading) Modifier else Modifier.verticalScroll(scrollState))
}