package mk.digital.kmpshowcase.presentation.screen.register

import mk.digital.kmpshowcase.presentation.base.BaseScreenshotTest
import mk.digital.kmpshowcase.presentation.base.ScreenshotMode
import mk.digital.kmpshowcase.presentation.base.StateHolder
import mk.digital.kmpshowcase.presentation.base.generateParameterizedData
import org.junit.Test
import org.robolectric.ParameterizedRobolectricTestRunner

class RegisterScreenScreenshotTest(
    stateHolder: StateHolder<RegisterUiState>,
    mode: ScreenshotMode,
) : BaseScreenshotTest<RegisterUiState>(stateHolder, mode) {

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun data(): Collection<*> = generateParameterizedData(RegisterScreenPreviewParams())
    }

    @Test
    fun registerScreen() {
        screenshot {
            RegisterScreen(state = state)
        }
    }
}
