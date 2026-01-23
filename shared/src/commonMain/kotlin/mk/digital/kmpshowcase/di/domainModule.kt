package mk.digital.kmpshowcase.di

import mk.digital.kmpshowcase.domain.useCase.GetUsersUseCase
import mk.digital.kmpshowcase.domain.useCase.TrackButtonClickUseCase
import mk.digital.kmpshowcase.domain.useCase.notes.DeleteAllNotesUseCase
import mk.digital.kmpshowcase.domain.useCase.notes.DeleteNoteUseCase
import mk.digital.kmpshowcase.domain.useCase.notes.InsertNoteUseCase
import mk.digital.kmpshowcase.domain.useCase.notes.ObserveNotesUseCase
import mk.digital.kmpshowcase.domain.useCase.notes.SearchNotesUseCase
import mk.digital.kmpshowcase.domain.useCase.notes.UpdateNoteUseCase
import mk.digital.kmpshowcase.domain.useCase.settings.GetThemeModeUseCase
import mk.digital.kmpshowcase.domain.useCase.settings.SetThemeModeUseCase
import mk.digital.kmpshowcase.domain.useCase.storage.ClearCacheUseCase
import mk.digital.kmpshowcase.domain.useCase.storage.LoadStorageDataUseCase
import mk.digital.kmpshowcase.domain.useCase.storage.ObserveStorageDataUseCase
import mk.digital.kmpshowcase.domain.useCase.storage.SetPersistentCounterUseCase
import mk.digital.kmpshowcase.domain.useCase.storage.SetSessionCounterUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    singleOf(::TrackButtonClickUseCase)
    singleOf(::GetUsersUseCase)
    singleOf(::LoadStorageDataUseCase)
    singleOf(::ObserveStorageDataUseCase)
    singleOf(::SetSessionCounterUseCase)
    singleOf(::SetPersistentCounterUseCase)
    singleOf(::ClearCacheUseCase)
    singleOf(::GetThemeModeUseCase)
    singleOf(::SetThemeModeUseCase)
    singleOf(::ObserveNotesUseCase)
    singleOf(::SearchNotesUseCase)
    singleOf(::InsertNoteUseCase)
    singleOf(::UpdateNoteUseCase)
    singleOf(::DeleteNoteUseCase)
    singleOf(::DeleteAllNotesUseCase)
}
