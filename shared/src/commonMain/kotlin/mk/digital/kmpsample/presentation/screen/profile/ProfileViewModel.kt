package mk.digital.kmpsample.presentation.screen.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import mk.digital.kmpsample.presentation.base.BaseViewModel

class ProfileViewModel : BaseViewModel<Unit>(Unit) {
    @Composable
    override fun toolbarTitle(): String = "Profile"
    override val navIcon: ImageVector? = null
}
