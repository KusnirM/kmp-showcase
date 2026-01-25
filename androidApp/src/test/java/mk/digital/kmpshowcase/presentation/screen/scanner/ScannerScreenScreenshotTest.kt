package mk.digital.kmpshowcase.presentation.screen.scanner

import mk.digital.kmpshowcase.presentation.base.BaseScreenshotTest
import mk.digital.kmpshowcase.presentation.base.ScreenshotMode
import mk.digital.kmpshowcase.presentation.base.StateHolder
import mk.digital.kmpshowcase.presentation.base.generateParameterizedData
import org.junit.Test
import org.robolectric.ParameterizedRobolectricTestRunner

class ScannerScreenScreenshotTest(
    stateHolder: StateHolder<ScannerUiState>,
    mode: ScreenshotMode,
) : BaseScreenshotTest<ScannerUiState>(stateHolder, mode) {

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun data(): Collection<*> = generateParameterizedData(ScannerScreenPreviewParams())
    }

    @Test
    fun scannerScreen() {
        screenshot {
            ScannerScreen(state = state)
        }
    }
}
