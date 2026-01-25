package mk.digital.kmpshowcase.presentation.screen.networking

import mk.digital.kmpshowcase.presentation.base.BaseScreenshotTest
import mk.digital.kmpshowcase.presentation.base.ScreenshotMode
import mk.digital.kmpshowcase.presentation.base.StateHolder
import mk.digital.kmpshowcase.presentation.base.generateParameterizedData
import org.junit.Test
import org.robolectric.ParameterizedRobolectricTestRunner

class NetworkingScreenScreenshotTest(
    stateHolder: StateHolder<NetworkingUiState>,
    mode: ScreenshotMode,
) : BaseScreenshotTest<NetworkingUiState>(stateHolder, mode) {

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun data(): Collection<*> = generateParameterizedData(NetworkingScreenPreviewParams())
    }

    @Test
    fun networkingScreen() {
        screenshot {
            NetworkingScreen(state = state)
        }
    }
}
