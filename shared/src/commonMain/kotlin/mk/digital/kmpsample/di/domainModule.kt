package mk.digital.kmpsample.di

import mk.digital.kmpsample.domain.useCase.LoadHomeDataUseCase
import mk.digital.kmpsample.domain.useCase.TrackButtonClickUseCase
import mk.digital.kmpsample.presentation.base.UseCaseRunner
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::LoadHomeDataUseCase)
    singleOf(::TrackButtonClickUseCase)
    singleOf(::UseCaseRunner)
}
