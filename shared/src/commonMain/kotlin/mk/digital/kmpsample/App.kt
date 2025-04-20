package mk.digital.kmpsample

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.stack.Children
import mk.digital.kmpsample.ui.base.AppComponent
import mk.digital.kmpsample.ui.foundation.AppTheme
import mk.digital.kmpsample.ui.screens.detail.DetailScreen
import mk.digital.kmpsample.ui.screens.home.HomeScreen


@Composable
fun MainView(component: AppComponent) {
    AppTheme {
        Children(stack = component.childStack) {
            when (val child = it.instance) {
                is AppComponent.Child.Home -> HomeScreen(child.component)
                is AppComponent.Child.Detail -> DetailScreen(child.component)
            }
        }
    }
}

expect fun getPlatformName(): String