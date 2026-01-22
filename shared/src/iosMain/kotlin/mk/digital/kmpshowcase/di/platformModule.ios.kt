package mk.digital.kmpshowcase.di

import mk.digital.kmpshowcase.data.biometric.BiometricClient
import mk.digital.kmpshowcase.data.biometric.IOSBiometricClient
import mk.digital.kmpshowcase.data.database.DatabaseDriverFactory
import mk.digital.kmpshowcase.data.local.preferences.Preferences
import mk.digital.kmpshowcase.data.local.preferences.PreferencesImpl
import mk.digital.kmpshowcase.data.location.IOSLocationClient
import mk.digital.kmpshowcase.data.location.LocationClient
import mk.digital.kmpshowcase.di.Qualifiers.app
import mk.digital.kmpshowcase.di.Qualifiers.session
import mk.digital.kmpshowcase.presentation.base.router.ExternalRouter
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule: Module = module {
    singleOf(::ExternalRouter)
    single<Preferences>(session) { PreferencesImpl(storageName = session.value) }
    single<Preferences>(app) { PreferencesImpl(storageName = app.value) }
    single<LocationClient> { IOSLocationClient() }
    single<BiometricClient> { IOSBiometricClient() }
    singleOf(::DatabaseDriverFactory)
}
