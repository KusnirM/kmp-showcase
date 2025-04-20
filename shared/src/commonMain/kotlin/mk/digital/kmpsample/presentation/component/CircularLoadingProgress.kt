package mk.digital.kmpsample.presentation.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import mk.digital.kmpsample.presentation.foundation.space8

@Composable
fun CircularProgress(
    modifier: Modifier = Modifier,
    size: Dp = space8,
) {
    CircularProgressIndicator(
        modifier = modifier.size(size),
        color = MaterialTheme.colors.primary,
        strokeWidth = circularProgressStrokeWidth
    )
}

private val circularProgressStrokeWidth = 3.dp
