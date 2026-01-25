package mk.digital.kmpshowcase.presentation.screen.settings

import mk.digital.kmpshowcase.presentation.base.BaseScreenshotTest
import mk.digital.kmpshowcase.presentation.base.ScreenshotMode
import mk.digital.kmpshowcase.presentation.base.StateHolder
import mk.digital.kmpshowcase.presentation.base.generateParameterizedData
import org.junit.Test
import org.robolectric.ParameterizedRobolectricTestRunner

class SettingsScreenScreenshotTest(
    stateHolder: StateHolder<SettingsState>,
    mode: ScreenshotMode,
) : BaseScreenshotTest<SettingsState>(stateHolder, mode) {

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun data(): Collection<*> = generateParameterizedData(SettingsScreenPreviewParams())
    }

    @Test
    fun settingsScreen() {
        screenshot {
            SettingsScreen(state = state)
        }
    }
}
