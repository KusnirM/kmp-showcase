package mk.digital.kmpshowcase.presentation.screen.login

import mk.digital.kmpshowcase.presentation.base.BaseScreenshotTest
import mk.digital.kmpshowcase.presentation.base.ScreenshotMode
import mk.digital.kmpshowcase.presentation.base.StateHolder
import mk.digital.kmpshowcase.presentation.base.generateParameterizedData
import org.junit.Test
import org.robolectric.ParameterizedRobolectricTestRunner

class LoginScreenScreenshotTest(
    stateHolder: StateHolder<LoginUiState>,
    mode: ScreenshotMode,
) : BaseScreenshotTest<LoginUiState>(stateHolder, mode) {

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun data(): Collection<*> = generateParameterizedData(LoginScreenPreviewParams())
    }

    @Test
    fun loginScreen() {
        screenshot {
            LoginScreen(state = state)
        }
    }
}
