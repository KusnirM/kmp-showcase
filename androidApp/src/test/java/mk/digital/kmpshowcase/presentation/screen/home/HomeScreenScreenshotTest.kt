package mk.digital.kmpshowcase.presentation.screen.home

import mk.digital.kmpshowcase.presentation.base.BaseScreenshotTest
import mk.digital.kmpshowcase.presentation.base.ScreenshotMode
import mk.digital.kmpshowcase.presentation.base.StateHolder
import mk.digital.kmpshowcase.presentation.base.generateParameterizedData
import org.junit.Test
import org.robolectric.ParameterizedRobolectricTestRunner

class HomeScreenScreenshotTest(
    stateHolder: StateHolder<HomeUiState>,
    mode: ScreenshotMode,
) : BaseScreenshotTest<HomeUiState>(stateHolder, mode) {

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun data(): Collection<*> = generateParameterizedData(HomeScreenPreviewParams())
    }

    @Test
    fun homeScreen() {
        screenshot {
            HomeScreen(state = state)
        }
    }
}
