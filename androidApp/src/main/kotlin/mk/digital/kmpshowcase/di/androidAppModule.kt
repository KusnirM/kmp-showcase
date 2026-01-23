package mk.digital.kmpshowcase.di

import mk.digital.kmpshowcase.data.analytics.AndroidAnalyticsClient
import mk.digital.kmpshowcase.data.analytics.AnalyticsClient
import mk.digital.kmpshowcase.data.location.AndroidLocationClient
import mk.digital.kmpshowcase.data.location.LocationClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidAppModule = module {
    single<AnalyticsClient> { AndroidAnalyticsClient(androidContext()) }
    single<LocationClient> { AndroidLocationClient(androidContext()) }
}
