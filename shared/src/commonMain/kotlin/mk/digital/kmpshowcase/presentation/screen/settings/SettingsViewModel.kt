package mk.digital.kmpshowcase.presentation.screen.settings

import mk.digital.kmpshowcase.domain.useCase.base.invoke
import mk.digital.kmpshowcase.domain.useCase.settings.GetThemeModeUseCase
import mk.digital.kmpshowcase.domain.useCase.settings.SetThemeModeUseCase
import mk.digital.kmpshowcase.presentation.base.BaseViewModel
import mk.digital.kmpshowcase.presentation.foundation.ThemeMode

data class SettingsState(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
)

class SettingsViewModel(
    private val getThemeModeUseCase: GetThemeModeUseCase,
    private val setThemeModeUseCase: SetThemeModeUseCase,
    private val onThemeChanged: (ThemeMode) -> Unit,
) : BaseViewModel<SettingsState>(SettingsState()) {

    override fun loadInitialData() {
        loadThemeMode()
    }

    private fun loadThemeMode() {
        execute(
            action = { getThemeModeUseCase() },
            onSuccess = { themeMode ->
                newState { it.copy(themeMode = themeMode) }
            }
        )
    }

    fun setThemeMode(mode: ThemeMode) {
        execute(
            action = { setThemeModeUseCase(mode) },
            onSuccess = {
                newState { it.copy(themeMode = mode) }
                onThemeChanged(mode)
            }
        )
    }
}
