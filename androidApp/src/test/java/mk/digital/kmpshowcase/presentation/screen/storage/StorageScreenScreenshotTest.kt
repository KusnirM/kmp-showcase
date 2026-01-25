package mk.digital.kmpshowcase.presentation.screen.storage

import mk.digital.kmpshowcase.presentation.base.BaseScreenshotTest
import mk.digital.kmpshowcase.presentation.base.ScreenshotMode
import mk.digital.kmpshowcase.presentation.base.StateHolder
import mk.digital.kmpshowcase.presentation.base.generateParameterizedData
import org.junit.Test
import org.robolectric.ParameterizedRobolectricTestRunner

class StorageScreenScreenshotTest(
    stateHolder: StateHolder<StorageUiState>,
    mode: ScreenshotMode,
) : BaseScreenshotTest<StorageUiState>(stateHolder, mode) {

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun data(): Collection<*> = generateParameterizedData(StorageScreenPreviewParams())
    }

    @Test
    fun storageScreen() {
        screenshot {
            StorageScreen(state = state)
        }
    }
}
