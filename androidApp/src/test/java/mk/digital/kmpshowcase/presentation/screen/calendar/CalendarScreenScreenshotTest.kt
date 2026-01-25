package mk.digital.kmpshowcase.presentation.screen.calendar

import mk.digital.kmpshowcase.presentation.base.BaseScreenshotTest
import mk.digital.kmpshowcase.presentation.base.ScreenshotMode
import mk.digital.kmpshowcase.presentation.base.StateHolder
import mk.digital.kmpshowcase.presentation.base.generateParameterizedData
import org.junit.Test
import org.robolectric.ParameterizedRobolectricTestRunner

class CalendarScreenScreenshotTest(
    stateHolder: StateHolder<CalendarUiState>,
    mode: ScreenshotMode,
) : BaseScreenshotTest<CalendarUiState>(stateHolder, mode) {

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters
        fun data(): Collection<*> = generateParameterizedData(CalendarScreenPreviewParams())
    }

    @Test
    fun calendarScreen() {
        screenshot {
            CalendarScreen(state = state)
        }
    }
}
