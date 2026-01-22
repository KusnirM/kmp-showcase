package mk.digital.kmpshowcase.presentation.di

import mk.digital.kmpshowcase.presentation.component.barcode.CodeGenerator
import mk.digital.kmpshowcase.presentation.component.imagepicker.ImagePickerViewModel
import mk.digital.kmpshowcase.presentation.foundation.ThemeMode
import mk.digital.kmpshowcase.presentation.screen.database.DatabaseViewModel
import mk.digital.kmpshowcase.presentation.screen.detail.DetailViewModel
import mk.digital.kmpshowcase.presentation.screen.home.HomeViewModel
import mk.digital.kmpshowcase.presentation.screen.networking.NetworkingViewModel
import mk.digital.kmpshowcase.presentation.screen.platformapis.PlatformApisViewModel
import mk.digital.kmpshowcase.presentation.screen.scanner.ScannerViewModel
import mk.digital.kmpshowcase.presentation.screen.settings.SettingsViewModel
import mk.digital.kmpshowcase.presentation.screen.storage.StorageViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    factory { CodeGenerator() }

    viewModel { HomeViewModel() }
    viewModel { DetailViewModel(id = it.get()) }
    viewModel { NetworkingViewModel(get()) }
    viewModel { StorageViewModel(get(), get(), get(), get(), get()) }
    viewModel { PlatformApisViewModel(get(), get(), get()) }
    viewModel { ScannerViewModel(get()) }
    viewModel { (onThemeChanged: (ThemeMode) -> Unit) ->
        SettingsViewModel(get(), get(), get(), onThemeChanged)
    }
    viewModel { ImagePickerViewModel() }
    viewModel { DatabaseViewModel(get(), get(), get(), get()) }
}
