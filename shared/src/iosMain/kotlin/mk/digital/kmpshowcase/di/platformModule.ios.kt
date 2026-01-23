package mk.digital.kmpshowcase.di

import mk.digital.kmpshowcase.data.analytics.AnalyticsClient
import mk.digital.kmpshowcase.data.analytics.IOSAnalyticsClient
import mk.digital.kmpshowcase.data.biometric.BiometricClient
import mk.digital.kmpshowcase.data.biometric.BiometricClientImpl
import mk.digital.kmpshowcase.data.database.DatabaseDriverFactory
import mk.digital.kmpshowcase.data.local.preferences.Preferences
import mk.digital.kmpshowcase.data.local.preferences.PreferencesImpl
import mk.digital.kmpshowcase.data.location.LocationClient
import mk.digital.kmpshowcase.data.location.LocationClientImpl
import mk.digital.kmpshowcase.di.Qualifiers.app
import mk.digital.kmpshowcase.di.Qualifiers.session
import mk.digital.kmpshowcase.presentation.base.router.ExternalRouter
import org.koin.core.module.Module
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule: Module = module {
    singleOf(::ExternalRouter)
    singleOf(::DatabaseDriverFactory)

    // Qualified preferences
    single<Preferences>(session) { PreferencesImpl(session.value) }
    single<Preferences>(app) { PreferencesImpl(app.value) }

    // Platform clients
    singleOf(::LocationClientImpl) { bind<LocationClient>() }
    singleOf(::BiometricClientImpl) { bind<BiometricClient>() }
    singleOf(::IOSAnalyticsClient) { bind<AnalyticsClient>() }
}
