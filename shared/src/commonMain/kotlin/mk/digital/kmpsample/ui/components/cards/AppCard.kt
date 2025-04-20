package mk.digital.kmpsample.ui.components.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mk.digital.kmpsample.ui.foundation.cardCornerRadius6
import mk.digital.kmpsample.ui.foundation.cardElevation


@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    elevated: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background,
        elevation = if (elevated) cardElevation else 0.dp,
        content = {
            Column(
                modifier = Modifier.padding(contentPadding),
                content = content
            )
        },
        shape = RoundedCornerShape(cardCornerRadius6)
    )
}
