package mk.digital.kmpshowcase.presentation.screen.settings

import androidx.compose.runtime.Composable

@Composable
expect fun LanguageSelector(
    currentLanguage: LanguageState,
    onNavigate: (SettingNavEvents) -> Unit,
)
