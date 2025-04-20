package mk.digital.kmpsample.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kmpsample.shared.generated.resources.Res
import kmpsample.shared.generated.resources.loading
import mk.digital.kmpsample.presentation.component.text.body1.TextBody1Neutral80
import mk.digital.kmpsample.presentation.foundation.appColors
import mk.digital.kmpsample.presentation.foundation.space4
import org.jetbrains.compose.resources.stringResource

@Composable
fun LoadingView() {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.appColors.neutral0)
            .padding(horizontal = space4),
        verticalAlignment = Alignment.CenterVertically,

        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgress()
        TextBody1Neutral80(
            text = stringResource(Res.string.loading),
            modifier = Modifier.padding(start = space4)
        )
    }
}
