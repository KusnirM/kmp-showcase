package mk.digital.kmpsample.di

import mk.digital.kmpsample.domain.GetWelcomeMessageUseCase
import mk.digital.kmpsample.ui.screens.detail.DetailViewModel
import mk.digital.kmpsample.ui.screens.home.HomeViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

expect fun platformModule(): Module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        modules(commonModule())
        appDeclaration()
    }

// called by iOS client
fun initKoin() = initKoin {}

fun commonModule() = module {
    includes(platformModule())
    singleOf(::GetWelcomeMessageUseCase)
    viewModel { DetailViewModel() }
    viewModel { HomeViewModel(get()) }
}

