package mk.digital.kmpshowcase.domain.useCase.settings

import mk.digital.kmpshowcase.domain.repository.SettingsRepository
import mk.digital.kmpshowcase.domain.useCase.base.UseCase
import mk.digital.kmpshowcase.presentation.foundation.ThemeMode

class SetThemeModeUseCase(
    private val settingsRepository: SettingsRepository
) : UseCase<ThemeMode, Unit>() {
    override suspend fun run(params: ThemeMode) = settingsRepository.setThemeMode(params)
}
