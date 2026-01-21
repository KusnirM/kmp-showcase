package mk.digital.kmpsample.presentation.di

import mk.digital.kmpsample.presentation.screen.detail.DetailViewModel
import mk.digital.kmpsample.presentation.screen.explore.ExploreViewModel
import mk.digital.kmpsample.presentation.screen.home.HomeViewModel
import mk.digital.kmpsample.presentation.screen.profile.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { ExploreViewModel() }
    viewModel { ProfileViewModel() }
    viewModel { DetailViewModel(id = it.get()) }
}
