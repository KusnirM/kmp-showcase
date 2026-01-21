package mk.digital.kmpshowcase

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import mk.digital.kmpshowcase.presentation.base.NavRouter
import mk.digital.kmpshowcase.presentation.base.Route
import mk.digital.kmpshowcase.presentation.base.Route.HomeSection
import mk.digital.kmpshowcase.presentation.base.Route.Settings
import mk.digital.kmpshowcase.presentation.base.WithViewModel
import mk.digital.kmpshowcase.presentation.base.rememberNavEntryDecorators
import mk.digital.kmpshowcase.presentation.base.rememberNavRouter
import mk.digital.kmpshowcase.presentation.component.TopAppBar
import mk.digital.kmpshowcase.presentation.component.image.AppIcon
import mk.digital.kmpshowcase.presentation.component.text.bodyLarge.TextBodyLarge
import mk.digital.kmpshowcase.presentation.foundation.AppTheme
import mk.digital.kmpshowcase.presentation.foundation.appColorScheme
import mk.digital.kmpshowcase.presentation.screen.feature.NetworkingScreen
import mk.digital.kmpshowcase.presentation.screen.feature.PlatformApisScreen
import mk.digital.kmpshowcase.presentation.screen.feature.StorageScreen
import mk.digital.kmpshowcase.presentation.screen.feature.UiComponentsScreen
import mk.digital.kmpshowcase.presentation.screen.home.HomeNavEvents
import mk.digital.kmpshowcase.presentation.screen.home.HomeScreen
import mk.digital.kmpshowcase.presentation.screen.home.HomeViewModel
import mk.digital.kmpshowcase.presentation.screen.settings.SettingsScreen
import org.jetbrains.compose.resources.stringResource


private val saveStateConfiguration = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(HomeSection.Home.serializer())
            subclass(HomeSection.UiComponents.serializer())
            subclass(HomeSection.Networking.serializer())
            subclass(HomeSection.Storage.serializer())
            subclass(HomeSection.PlatformApis.serializer())
            subclass(Settings.serializer())
        }
    }
}

@Composable
fun MainView() {
    val router: NavRouter<Route> = rememberNavRouter(saveStateConfiguration, HomeSection.Home)
    val currentRoute: Route = router.backStack.last()

    AppTheme {
        Scaffold(
            contentWindowInsets = WindowInsets(0),
            topBar = {
                TopAppBar(
                    title = stringResource(currentRoute.titleRes),
                    navIcon = if (currentRoute.showBackArrow) Icons.AutoMirrored.Filled.ArrowBack else null,
                    backClick = router::onBack,
                )
            },
            bottomBar = {
                BottomBarNavigation(
                    current = currentRoute,
                    onNavigate = { route ->
                        val shouldNavigate = currentRoute != route
                        if (shouldNavigate) {
                            val includeHome = route is HomeSection.Home
                            router.navigateTo(
                                page = route,
                                popUpTo = HomeSection.Home::class,
                                inclusive = includeHome
                            )
                        }
                    }
                )
            },
        ) { contentPadding ->
            NavDisplay(
                modifier = Modifier.padding(contentPadding),
                backStack = router.backStack,
                onBack = router::onBack,
                entryDecorators = rememberNavEntryDecorators(),
                entryProvider = entryProvider {
                    entry<HomeSection.Home> {
                        WithViewModel<HomeViewModel> { viewModel ->
                            HomeNavEvents(viewModel, router)
                            HomeScreen(viewModel)
                        }
                    }
                    entry<HomeSection.UiComponents> { UiComponentsScreen() }
                    entry<HomeSection.Networking> { NetworkingScreen() }
                    entry<HomeSection.Storage> { StorageScreen() }
                    entry<HomeSection.PlatformApis> { PlatformApisScreen() }
                    entry<Settings> {
                        SettingsScreen()
                    }
                }
            )
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
    val selectedColor = MaterialTheme.colorScheme.primary
    val unselectedColor = MaterialTheme.appColorScheme.neutral80
    NavigationBarItem(
        selected = selected,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = selectedColor,
            selectedTextColor = selectedColor,
            unselectedIconColor = unselectedColor,
            unselectedTextColor = unselectedColor,
            indicatorColor = MaterialTheme.appColorScheme.neutral20
        ),
        onClick = onClick,
        icon = {
            AppIcon(imageVector = icon)
        },
        label = {
            TextBodyLarge(text = label, color = Color.Unspecified)
        }
    )
}

@Composable
fun BottomBarNavigation(
    current: Route,
    onNavigate: (Route) -> Unit
) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.background) {
        AppBottomNavigationItem(
            selected = current is HomeSection,
            onClick = { onNavigate(HomeSection.Home) },
            icon = Icons.Filled.Home,
            label = "Home"
        )
        AppBottomNavigationItem(
            selected = current is Settings,
            onClick = { onNavigate(Settings) },
            icon = Icons.Filled.Settings,
            label = "Settings"
        )
    }
}
