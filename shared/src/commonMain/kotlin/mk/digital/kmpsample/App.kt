package mk.digital.kmpsample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import mk.digital.kmpsample.presentation.base.AppComponent
import mk.digital.kmpsample.presentation.base.NavConfig
import mk.digital.kmpsample.presentation.component.image.AppIcon
import mk.digital.kmpsample.presentation.component.text.bodyLarge.TextBodyLargeNeutral80
import mk.digital.kmpsample.presentation.foundation.AppTheme
import mk.digital.kmpsample.presentation.foundation.appColors
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
private fun RowScope.AppBottomNavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: ImageVector,
    label: String
) {
    NavigationBarItem(
        selected = selected,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.secondary,
            unselectedIconColor = MaterialTheme.colorScheme.secondary,
            indicatorColor = MaterialTheme.appColors.transparent
        ),
        onClick = onClick,
        icon = {
            AppIcon(imageVector = icon)
        },
        label = {
            TextBodyLargeNeutral80(text = label)
        }
    )
}

@Composable
fun BottomBarNavigation(
    current: NavConfig,
    onNavigate: (NavConfig) -> Unit
) {
    NavigationBar(
        modifier = Modifier.navigationBarsPadding()
    ) {
        AppBottomNavigationItem(
            selected = current is NavConfig.HomeSection.Home,
            onClick = { onNavigate(NavConfig.HomeSection.Home) },
            icon = Icons.Filled.Home,
            label = "Home"
        )
        AppBottomNavigationItem(
            selected = current is NavConfig.Explore,
            onClick = { onNavigate(NavConfig.Explore) },
            icon = Icons.Outlined.Search,
            label = "Explore"
        )
        AppBottomNavigationItem(
            selected = current is NavConfig.Profile,
            onClick = { onNavigate(NavConfig.Profile) },
            icon = Icons.Filled.Person,
            label = "Profile"
        )
    }
}

expect fun getPlatformName(): String