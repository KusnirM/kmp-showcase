package mk.digital.kmpshowcase.data.repository

import mk.digital.kmpshowcase.data.local.preferences.AppPreferences
import mk.digital.kmpshowcase.domain.repository.SettingsRepository
import mk.digital.kmpshowcase.presentation.foundation.ThemeMode

class SettingsRepositoryImpl(
    private val appPreferences: AppPreferences
) : SettingsRepository {

    override suspend fun getThemeMode(): ThemeMode {
        val mode = appPreferences.getThemeMode()
        return ThemeMode.entries.find { it.name == mode } ?: ThemeMode.SYSTEM
    }

    override suspend fun setThemeMode(mode: ThemeMode) {
        appPreferences.setThemeMode(mode.name)
    }
}
