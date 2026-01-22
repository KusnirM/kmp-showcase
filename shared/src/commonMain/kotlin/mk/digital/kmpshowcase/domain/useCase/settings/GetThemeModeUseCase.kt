package mk.digital.kmpshowcase.domain.useCase.settings

import mk.digital.kmpshowcase.domain.repository.SettingsRepository
import mk.digital.kmpshowcase.domain.useCase.base.None
import mk.digital.kmpshowcase.domain.useCase.base.UseCase
import mk.digital.kmpshowcase.presentation.foundation.ThemeMode

class GetThemeModeUseCase(
    private val settingsRepository: SettingsRepository
) : UseCase<None, ThemeMode>() {
    override suspend fun run(params: None): ThemeMode = settingsRepository.getThemeMode()
}
