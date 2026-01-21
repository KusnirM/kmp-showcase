package mk.digital.kmpshowcase.presentation.screen.feature

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import mk.digital.kmpshowcase.presentation.base.BaseViewModel

class UiComponentsViewModel : BaseViewModel<Unit>(Unit) {
    @Composable
    override fun toolbarTitle(): String = "UI Components"
    override val navIcon: ImageVector = Icons.AutoMirrored.Filled.ArrowBack
}

class NetworkingViewModel : BaseViewModel<Unit>(Unit) {
    @Composable
    override fun toolbarTitle(): String = "Networking"
    override val navIcon: ImageVector = Icons.AutoMirrored.Filled.ArrowBack
}

class StorageViewModel : BaseViewModel<Unit>(Unit) {
    @Composable
    override fun toolbarTitle(): String = "Storage"
    override val navIcon: ImageVector = Icons.AutoMirrored.Filled.ArrowBack
}

class PlatformApisViewModel : BaseViewModel<Unit>(Unit) {
    @Composable
    override fun toolbarTitle(): String = "Platform APIs"
    override val navIcon: ImageVector = Icons.AutoMirrored.Filled.ArrowBack
}
