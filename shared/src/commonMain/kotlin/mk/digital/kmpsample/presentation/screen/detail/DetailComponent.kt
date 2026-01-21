package mk.digital.kmpsample.presentation.screen.detail

import androidx.compose.runtime.Composable
import mk.digital.kmpsample.presentation.base.BaseComponentContext


class DetailComponent(
    val id: Int,
) : BaseComponentContext<Unit>(Unit) {
    @Composable
    override fun toolbarTitle(): String = id.toString()
}
