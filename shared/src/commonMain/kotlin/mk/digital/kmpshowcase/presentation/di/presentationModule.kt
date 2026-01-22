package mk.digital.kmpshowcase.presentation.di

import mk.digital.kmpshowcase.presentation.screen.detail.DetailViewModel
import mk.digital.kmpshowcase.presentation.screen.networking.NetworkingViewModel
import mk.digital.kmpshowcase.presentation.screen.home.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HomeViewModel() }
    viewModel { DetailViewModel(id = it.get()) }
    viewModel { NetworkingViewModel(get()) }
}
