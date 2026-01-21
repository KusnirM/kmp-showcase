package mk.digital.kmpsample.presentation.screen.detail

import androidx.compose.runtime.Composable
import mk.digital.kmpsample.presentation.base.BaseViewModel

class DetailViewModel(
    val id: Int,
) : BaseViewModel<Unit>(Unit) {
    @Composable
    override fun toolbarTitle(): String = id.toString()
}
