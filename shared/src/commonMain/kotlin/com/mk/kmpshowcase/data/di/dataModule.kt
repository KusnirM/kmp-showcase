package com.mk.kmpshowcase.data.di

import io.ktor.client.HttpClient
import com.mk.kmpshowcase.data.database.AppDatabase
import com.mk.kmpshowcase.data.database.DatabaseDriverFactory
import com.mk.kmpshowcase.data.local.StorageLocalStore
import com.mk.kmpshowcase.data.local.StorageLocalStoreImpl
import com.mk.kmpshowcase.data.local.preferences.PersistentPreferences
import com.mk.kmpshowcase.data.local.preferences.PersistentPreferencesImpl
import com.mk.kmpshowcase.data.local.preferences.SessionPreferences
import com.mk.kmpshowcase.data.local.preferences.SessionPreferencesImpl
import com.mk.kmpshowcase.data.network.HttpClientProvider
import com.mk.kmpshowcase.data.notification.NotificationRepositoryImpl
import com.mk.kmpshowcase.data.repository.BiometricRepositoryImpl
import com.mk.kmpshowcase.data.repository.DateRepositoryImpl
import com.mk.kmpshowcase.data.repository.FlashlightRepositoryImpl
import com.mk.kmpshowcase.data.repository.LocationRepositoryImpl
import com.mk.kmpshowcase.data.repository.SettingsRepositoryImpl
import com.mk.kmpshowcase.data.repository.database.AuthRepositoryImpl
import com.mk.kmpshowcase.data.repository.database.NoteRepositoryImpl
import com.mk.kmpshowcase.data.repository.example.ExampleClient
import com.mk.kmpshowcase.data.repository.example.ExampleClientImpl
import com.mk.kmpshowcase.data.repository.example.ExampleRepositoryImpl
import com.mk.kmpshowcase.data.repository.storage.StorageRepositoryImpl
import com.mk.kmpshowcase.data.repository.user.UserClient
import com.mk.kmpshowcase.data.repository.user.UserClientImpl
import com.mk.kmpshowcase.data.repository.user.UserRepositoryImpl
import com.mk.kmpshowcase.di.Qualifiers.app
import com.mk.kmpshowcase.di.Qualifiers.session
import com.mk.kmpshowcase.domain.repository.AuthRepository
import com.mk.kmpshowcase.domain.repository.BiometricRepository
import com.mk.kmpshowcase.domain.repository.DateRepository
import com.mk.kmpshowcase.domain.repository.ExampleRepository
import com.mk.kmpshowcase.domain.repository.FlashlightRepository
import com.mk.kmpshowcase.domain.repository.LocationRepository
import com.mk.kmpshowcase.domain.repository.NoteRepository
import com.mk.kmpshowcase.domain.repository.NotificationRepository
import com.mk.kmpshowcase.domain.repository.SettingsRepository
import com.mk.kmpshowcase.domain.repository.StorageRepository
import com.mk.kmpshowcase.domain.repository.UserRepository
import com.mk.kmpshowcase.util.Logger
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::Logger)
    singleOf(::UserClientImpl) { bind<UserClient>() }
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    singleOf(::ExampleClientImpl) { bind<ExampleClient>() }
    singleOf(::ExampleRepositoryImpl) { bind<ExampleRepository>() }
    single { provideHttpClient() }

    // Qualified preferences - need explicit qualifier
    single<SessionPreferences> { SessionPreferencesImpl(get(session)) }
    single<PersistentPreferences> { PersistentPreferencesImpl(get(app)) }

    singleOf(::StorageLocalStoreImpl) { bind<StorageLocalStore>() }
    singleOf(::StorageRepositoryImpl) { bind<StorageRepository>() }
    singleOf(::SettingsRepositoryImpl) { bind<SettingsRepository>() }
    singleOf(::LocationRepositoryImpl) { bind<LocationRepository>() }
    singleOf(::BiometricRepositoryImpl) { bind<BiometricRepository>() }
    singleOf(::FlashlightRepositoryImpl) { bind<FlashlightRepository>() }
    singleOf(::DateRepositoryImpl) { bind<DateRepository>() }
    singleOf(::NoteRepositoryImpl) { bind<NoteRepository>() }
    singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }
    singleOf(::NotificationRepositoryImpl) { bind<NotificationRepository>() }

    // Database - needs special factory
    single { AppDatabase(get<DatabaseDriverFactory>().createDriver()) }
}

fun provideHttpClient(): HttpClient = HttpClientProvider().create()
