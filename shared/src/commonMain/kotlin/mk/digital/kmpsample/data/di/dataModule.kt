package mk.digital.kmpsample.data.di

import io.ktor.client.HttpClient
import mk.digital.kmpsample.data.network.HttpClientProvider
import mk.digital.kmpsample.data.repository.user.UserClient
import mk.digital.kmpsample.data.repository.user.UserClientImpl
import mk.digital.kmpsample.data.repository.user.UserRepositoryImpl
import mk.digital.kmpsample.domain.repository.UserRepository
import org.koin.dsl.module

val dataModule = module {
    single<UserClient> { UserClientImpl(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single { provideHttpClient() }
}

fun provideHttpClient(): HttpClient = HttpClientProvider().create()
