package mk.digital.kmpsample.presentation.component.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import mk.digital.kmpsample.presentation.foundation.cardElevation


@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    elevated: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    val shape = MaterialTheme.shapes.medium
    Card(
        backgroundColor = MaterialTheme.colors.surface,
        elevation = if (elevated) cardElevation else 0.dp,
        content = {
            Column(
                modifier = Modifier.clip(shape).then(modifier),
                content = content
            )
        },
        shape = shape
    )
}
