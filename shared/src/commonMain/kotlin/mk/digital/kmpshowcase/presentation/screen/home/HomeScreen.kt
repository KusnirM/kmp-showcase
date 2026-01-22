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
import mk.digital.kmpshowcase.presentation.foundation.space4
import mk.digital.kmpshowcase.presentation.foundation.space8

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LazyColumn(
        contentPadding = PaddingValues(vertical = space8, horizontal = space4),
        verticalArrangement = Arrangement.spacedBy(space8)
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
fun HomeNavEvents(
    viewModel: HomeViewModel,
    router: NavRouter<Route>
) {
    CollectNavEvents(navEventFlow = viewModel.navEvent) {
        if (it !is HomeNavEvent) return@CollectNavEvents
        when (it) {
            is HomeNavEvent.ToFeature -> {
                when (it.featureId) {
                    FeatureId.UI_COMPONENTS -> router.navigateTo(Route.HomeSection.UiComponents)
                    FeatureId.NETWORKING -> router.navigateTo(Route.HomeSection.Networking)
                    FeatureId.STORAGE -> router.navigateTo(Route.HomeSection.Storage)
                    FeatureId.DATABASE -> router.navigateTo(Route.HomeSection.Database)
                    FeatureId.PLATFORM_APIS -> router.navigateTo(Route.HomeSection.PlatformApis)
                    FeatureId.SCANNER -> router.navigateTo(Route.HomeSection.Scanner)
                }
            }
        }
    }
}
