package mk.digital.kmpshowcase.presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mk.digital.kmpshowcase.presentation.base.CollectNavEvents
import mk.digital.kmpshowcase.presentation.base.NavRouter
import mk.digital.kmpshowcase.presentation.base.Route
import mk.digital.kmpshowcase.presentation.base.lifecycleAwareViewModel
import mk.digital.kmpshowcase.presentation.foundation.floatingNavBarSpace
import mk.digital.kmpshowcase.presentation.foundation.space4

@Composable
fun HomeScreen(router: NavRouter<Route>,viewModel: HomeViewModel = lifecycleAwareViewModel()) {
    HomeNavEvents(router)
    val state by viewModel.state.collectAsStateWithLifecycle()

    LazyColumn(
        contentPadding = PaddingValues(
            start = space4,
            end = space4,
            top = space4,
            bottom = floatingNavBarSpace,
        ),
        verticalArrangement = Arrangement.spacedBy(space4),
    ) {
        items(state.features, key = { it.id }) { feature ->
            FeatureCard(
                feature = feature,
                onClick = { viewModel.onFeatureClick(feature.id) }
            )
        }
    }
}

@Composable
private fun HomeNavEvents(
    router: NavRouter<Route>,
    viewModel: HomeViewModel = lifecycleAwareViewModel(),
) {
    CollectNavEvents(navEventFlow = viewModel.navEvent) {
        when (it) {
            is HomeNavEvent.ToFeature -> {
                when (it.featureId) {
                    FeatureId.UI_COMPONENTS -> router.navigateTo(Route.HomeSection.UiComponents)
                    FeatureId.NETWORKING -> router.navigateTo(Route.HomeSection.Networking)
                    FeatureId.STORAGE -> router.navigateTo(Route.HomeSection.Storage)
                    FeatureId.DATABASE -> router.navigateTo(Route.HomeSection.Database)
                    FeatureId.PLATFORM_APIS -> router.navigateTo(Route.HomeSection.PlatformApis)
                    FeatureId.SCANNER -> router.navigateTo(Route.HomeSection.Scanner)
                    FeatureId.CALENDAR -> router.navigateTo(Route.HomeSection.Calendar)
                    FeatureId.NOTIFICATIONS -> router.navigateTo(Route.HomeSection.Notifications)
                }
            }
        }
    }
}
