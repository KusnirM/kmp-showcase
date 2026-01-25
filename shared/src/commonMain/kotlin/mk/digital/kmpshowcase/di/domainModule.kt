package mk.digital.kmpshowcase.di

import mk.digital.kmpshowcase.domain.useCase.GetUsersUseCase
import mk.digital.kmpshowcase.domain.useCase.TrackButtonClickUseCase
import mk.digital.kmpshowcase.domain.useCase.analytics.TrackScreenUseCase
import mk.digital.kmpshowcase.domain.useCase.auth.CheckEmailExistsUseCase
import mk.digital.kmpshowcase.domain.useCase.auth.RegisterUserUseCase
import mk.digital.kmpshowcase.domain.useCase.biometric.AuthenticateWithBiometricUseCase
import mk.digital.kmpshowcase.domain.useCase.biometric.IsBiometricEnabledUseCase
import mk.digital.kmpshowcase.domain.useCase.calendar.GetTodayDateUseCase
import mk.digital.kmpshowcase.domain.useCase.flashlight.IsFlashlightAvailableUseCase
import mk.digital.kmpshowcase.domain.useCase.flashlight.ToggleFlashlightUseCase
import mk.digital.kmpshowcase.domain.useCase.flashlight.TurnOffFlashlightUseCase
import mk.digital.kmpshowcase.domain.useCase.location.GetLastKnownLocationUseCase
import mk.digital.kmpshowcase.domain.useCase.location.ObserveLocationUpdatesUseCase
import mk.digital.kmpshowcase.domain.useCase.notes.DeleteAllNotesUseCase
import mk.digital.kmpshowcase.domain.useCase.notes.DeleteNoteUseCase
import mk.digital.kmpshowcase.domain.useCase.notes.InsertNoteUseCase
import mk.digital.kmpshowcase.domain.useCase.notes.ObserveNotesUseCase
import mk.digital.kmpshowcase.domain.useCase.notes.SearchNotesUseCase
import mk.digital.kmpshowcase.domain.useCase.notes.UpdateNoteUseCase
import mk.digital.kmpshowcase.domain.useCase.notifications.CancelAllNotificationsUseCase
import mk.digital.kmpshowcase.domain.useCase.notifications.GetPushPermissionStatusUseCase
import mk.digital.kmpshowcase.domain.useCase.notifications.LogPushTokenUseCase
import mk.digital.kmpshowcase.domain.useCase.notifications.ObservePushNotificationsUseCase
import mk.digital.kmpshowcase.domain.useCase.notifications.ObservePushTokenUseCase
import mk.digital.kmpshowcase.domain.useCase.notifications.RefreshPushTokenUseCase
import mk.digital.kmpshowcase.domain.useCase.notifications.ShowLocalNotificationUseCase
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
    singleOf(::TrackScreenUseCase)
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
    singleOf(::GetTodayDateUseCase)
    singleOf(::IsBiometricEnabledUseCase)
    singleOf(::AuthenticateWithBiometricUseCase)
    singleOf(::CheckEmailExistsUseCase)
    singleOf(::RegisterUserUseCase)

    // Location
    singleOf(::GetLastKnownLocationUseCase)
    singleOf(::ObserveLocationUpdatesUseCase)

    // Flashlight
    singleOf(::IsFlashlightAvailableUseCase)
    singleOf(::ToggleFlashlightUseCase)
    singleOf(::TurnOffFlashlightUseCase)

    // Notifications
    singleOf(::GetPushPermissionStatusUseCase)
    singleOf(::ObservePushTokenUseCase)
    singleOf(::ObservePushNotificationsUseCase)
    singleOf(::RefreshPushTokenUseCase)
    singleOf(::LogPushTokenUseCase)
    singleOf(::ShowLocalNotificationUseCase)
    singleOf(::CancelAllNotificationsUseCase)
}
