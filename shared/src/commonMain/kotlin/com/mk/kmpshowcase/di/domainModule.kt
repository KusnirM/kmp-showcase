package com.mk.kmpshowcase.di

import com.mk.kmpshowcase.domain.useCase.GetUsersUseCase
import com.mk.kmpshowcase.domain.useCase.TrackButtonClickUseCase
import com.mk.kmpshowcase.domain.useCase.analytics.TrackScreenUseCase
import com.mk.kmpshowcase.domain.useCase.auth.CheckEmailExistsUseCase
import com.mk.kmpshowcase.domain.useCase.auth.RegisterUserUseCase
import com.mk.kmpshowcase.domain.useCase.biometric.AuthenticateWithBiometricUseCase
import com.mk.kmpshowcase.domain.useCase.biometric.IsBiometricEnabledUseCase
import com.mk.kmpshowcase.domain.useCase.calendar.GetTodayDateUseCase
import com.mk.kmpshowcase.domain.useCase.flashlight.IsFlashlightAvailableUseCase
import com.mk.kmpshowcase.domain.useCase.flashlight.ToggleFlashlightUseCase
import com.mk.kmpshowcase.domain.useCase.flashlight.TurnOffFlashlightUseCase
import com.mk.kmpshowcase.domain.useCase.location.GetLastKnownLocationUseCase
import com.mk.kmpshowcase.domain.useCase.location.ObserveLocationUpdatesUseCase
import com.mk.kmpshowcase.domain.useCase.notes.DeleteAllNotesUseCase
import com.mk.kmpshowcase.domain.useCase.notes.DeleteNoteUseCase
import com.mk.kmpshowcase.domain.useCase.notes.InsertNoteUseCase
import com.mk.kmpshowcase.domain.useCase.notes.ObserveNotesUseCase
import com.mk.kmpshowcase.domain.useCase.notes.SearchNotesUseCase
import com.mk.kmpshowcase.domain.useCase.notes.UpdateNoteUseCase
import com.mk.kmpshowcase.domain.useCase.notifications.CancelAllNotificationsUseCase
import com.mk.kmpshowcase.domain.useCase.notifications.GetPushPermissionStatusUseCase
import com.mk.kmpshowcase.domain.useCase.notifications.LogPushTokenUseCase
import com.mk.kmpshowcase.domain.useCase.notifications.ObservePushNotificationsUseCase
import com.mk.kmpshowcase.domain.useCase.notifications.ObservePushTokenUseCase
import com.mk.kmpshowcase.domain.useCase.notifications.RefreshPushTokenUseCase
import com.mk.kmpshowcase.domain.useCase.notifications.ShowLocalNotificationUseCase
import com.mk.kmpshowcase.domain.useCase.settings.GetThemeModeUseCase
import com.mk.kmpshowcase.domain.useCase.settings.SetThemeModeUseCase
import com.mk.kmpshowcase.domain.useCase.storage.ClearCacheUseCase
import com.mk.kmpshowcase.domain.useCase.storage.LoadStorageDataUseCase
import com.mk.kmpshowcase.domain.useCase.storage.ObserveStorageDataUseCase
import com.mk.kmpshowcase.domain.useCase.storage.SetPersistentCounterUseCase
import com.mk.kmpshowcase.domain.useCase.storage.SetSessionCounterUseCase
import com.mk.kmpshowcase.domain.useCase.example.GetExamplesUseCase
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

    // Example
    singleOf(::GetExamplesUseCase)
}
