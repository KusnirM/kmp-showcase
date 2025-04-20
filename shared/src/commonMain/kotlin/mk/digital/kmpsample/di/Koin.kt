package mk.digital.kmpsample.di

import mk.digital.kmpsample.data.di.dataModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
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
    includes(
        listOf(
            platformModule(),
            domainModule,
            dataModule
        )
    )
}

