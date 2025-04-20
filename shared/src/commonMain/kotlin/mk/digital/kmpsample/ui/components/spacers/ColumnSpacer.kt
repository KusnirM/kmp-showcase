package mk.digital.kmpsample.ui.components.spacers

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import mk.digital.kmpsample.ui.foundation.space
import mk.digital.kmpsample.ui.foundation.space12
import mk.digital.kmpsample.ui.foundation.space2
import mk.digital.kmpsample.ui.foundation.space3
import mk.digital.kmpsample.ui.foundation.space4
import mk.digital.kmpsample.ui.foundation.space6
import mk.digital.kmpsample.ui.foundation.space8


object ColumnSpacer {
    @Composable
    private fun ColumnScope.Spacer(height: Dp) {
        Spacer(modifier = Modifier.height(height))
    }

    @Composable
    fun ColumnScope.Spacer1() {
        Spacer(space)
    }

    @Composable
    fun ColumnScope.Spacer2() {
        Spacer(space2)
    }

    @Composable
    fun ColumnScope.Spacer3() {
        Spacer(space3)
    }

    @Composable
    fun ColumnScope.Spacer4() {
        Spacer(space4)
    }


    @Composable
    fun ColumnScope.Spacer6() {
        Spacer(space6)
    }


    @Composable
    fun ColumnScope.Spacer8() {
        Spacer(space8)
    }

    @Composable
    fun ColumnScope.Spacer12() {
        Spacer(space12)
    }


}
