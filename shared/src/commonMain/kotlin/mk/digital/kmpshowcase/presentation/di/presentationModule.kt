package mk.digital.kmpshowcase.presentation.di

import mk.digital.kmpshowcase.presentation.screen.detail.DetailViewModel
import mk.digital.kmpshowcase.presentation.screen.explore.ExploreViewModel
import mk.digital.kmpshowcase.presentation.screen.feature.NetworkingViewModel
import mk.digital.kmpshowcase.presentation.screen.feature.PlatformApisViewModel
import mk.digital.kmpshowcase.presentation.screen.feature.StorageViewModel
import mk.digital.kmpshowcase.presentation.screen.feature.UiComponentsViewModel
import mk.digital.kmpshowcase.presentation.screen.home.HomeViewModel
import mk.digital.kmpshowcase.presentation.screen.profile.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HomeViewModel() }
    viewModel { ExploreViewModel() }
    viewModel { ProfileViewModel() }
    viewModel { DetailViewModel(id = it.get()) }
    viewModelOf(::UiComponentsViewModel)
    viewModelOf(::NetworkingViewModel)
    viewModelOf(::StorageViewModel)
    viewModelOf(::PlatformApisViewModel)
}
