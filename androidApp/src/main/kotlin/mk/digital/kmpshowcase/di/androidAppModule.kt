package mk.digital.kmpshowcase.di

import mk.digital.kmpshowcase.data.location.AndroidLocationClient
import mk.digital.kmpshowcase.data.location.LocationClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidAppModule = module {
    single<LocationClient> { AndroidLocationClient(androidContext()) }
}
