package mk.digital.kmpshowcase.di

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import mk.digital.kmpshowcase.data.analytics.AnalyticsClient
import mk.digital.kmpshowcase.data.analytics.AndroidAnalyticsClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val androidAppModule = module {
    single { FirebaseAnalytics.getInstance(androidContext()) }
    single { FirebaseCrashlytics.getInstance() }
    singleOf(::AndroidAnalyticsClient) { bind<AnalyticsClient>() }
}
