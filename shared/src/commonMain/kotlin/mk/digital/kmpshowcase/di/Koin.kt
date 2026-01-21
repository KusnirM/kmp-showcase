package mk.digital.kmpshowcase.di

import mk.digital.kmpshowcase.data.di.dataModule
import mk.digital.kmpshowcase.presentation.di.presentationModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module


fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        modules(commonModule())
        appDeclaration()
    }

// called by iOS client
@Suppress("unused")
fun initKoin() = initKoin {}

fun commonModule() = module {
    includes(
        listOf(
            platformModule,
            presentationModule,
            domainModule,
            dataModule
        )
    )
}

