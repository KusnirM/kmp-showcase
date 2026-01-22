package mk.digital.kmpshowcase.di

import mk.digital.kmpshowcase.data.local.preferences.Preferences
import mk.digital.kmpshowcase.data.local.preferences.PreferencesImpl
import mk.digital.kmpshowcase.di.Qualifiers.app
import mk.digital.kmpshowcase.di.Qualifiers.session
import mk.digital.kmpshowcase.presentation.base.router.ExternalRouter
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule: Module = module {
    singleOf(::ExternalRouter)
    single<Preferences>(session) {
        PreferencesImpl(context = androidContext(), storageName = session.value)
    }
    single<Preferences>(app) {
        PreferencesImpl(context = androidContext(), storageName = app.value)
    }
}
