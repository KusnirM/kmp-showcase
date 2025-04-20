package mk.digital.kmpsample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import mk.digital.kmpsample.presentation.base.AppComponent
import mk.digital.kmpsample.presentation.base.NavConfig
import mk.digital.kmpsample.presentation.component.image.AppIcon
import mk.digital.kmpsample.presentation.foundation.AppTheme
import mk.digital.kmpsample.presentation.screen.detail.DetailScreen
import mk.digital.kmpsample.presentation.screen.explore.ExploreScreen
import mk.digital.kmpsample.presentation.screen.home.HomeScreen
import mk.digital.kmpsample.presentation.screen.profile.ProfileScreen


@Composable
fun MainView(component: AppComponent) {
    val stack by component.childStack.subscribeAsState()

    AppTheme {
        Scaffold(
            bottomBar = {
                BottomBarNavigation(
                    current = stack.active.configuration,
                    onNavigate = component::navigateTo
                )
            },
        ) { contentPadding ->
            Box(
                Modifier
                    .padding(contentPadding)
                    .windowInsetsPadding(WindowInsets.ime)
            ) {
                when (val child = stack.active.instance) {
                    is AppComponent.Child.Home -> HomeScreen(child.component)
                    is AppComponent.Child.Detail -> DetailScreen(child.component)
                    is AppComponent.Child.Profile -> ProfileScreen()
                    is AppComponent.Child.Explore -> ExploreScreen()
                }
            }
        }
    }
}

@Composable
fun BottomBarNavigation(
    current: NavConfig,
    onNavigate: (NavConfig) -> Unit
) {
    BottomNavigation(
        modifier = Modifier.navigationBarsPadding()
    ) {
        BottomNavigationItem(
            selected = current is NavConfig.HomeSection.Home,
            onClick = { onNavigate(NavConfig.HomeSection.Home) },
            icon = { AppIcon(Icons.Filled.Home) },
            label = { Text("Home") }
        )
        BottomNavigationItem(
            selected = current is NavConfig.Explore,
            onClick = { onNavigate(NavConfig.Explore) },
            icon = { AppIcon(Icons.Outlined.Search) },
            label = { Text("Explore") }
        )
        BottomNavigationItem(
            selected = current is NavConfig.Profile,
            onClick = { onNavigate(NavConfig.Profile) },
            icon = { AppIcon(Icons.Filled.Person) },
            label = { Text("Profile") }
        )
    }
}

expect fun getPlatformName(): String