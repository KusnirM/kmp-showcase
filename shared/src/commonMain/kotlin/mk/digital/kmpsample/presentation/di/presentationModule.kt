package mk.digital.kmpsample.presentation.di

import mk.digital.kmpsample.presentation.screen.detail.DetailComponent
import mk.digital.kmpsample.presentation.screen.explore.ExploreComponent
import mk.digital.kmpsample.presentation.screen.home.HomeComponent
import mk.digital.kmpsample.presentation.screen.profile.ProfileComponent
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HomeComponent(get(), get(), get()) }
    viewModel { ExploreComponent() }
    viewModel { ProfileComponent() }
    viewModel { DetailComponent(id = it.get()) }
}