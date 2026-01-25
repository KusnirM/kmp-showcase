package mk.digital.kmpshowcase.domain.useCase.flashlight

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import mk.digital.kmpshowcase.domain.BaseTest
import mk.digital.kmpshowcase.domain.repository.FlashlightRepository
import mk.digital.kmpshowcase.domain.test
import mk.digital.kmpshowcase.domain.useCase.base.invoke
import kotlin.test.Test
import kotlin.test.assertEquals

class ToggleFlashlightUseCaseTest : BaseTest<ToggleFlashlightUseCase>() {
    override lateinit var classUnderTest: ToggleFlashlightUseCase

    private val flashlightRepository: FlashlightRepository = mock()

    override fun beforeEach() {
        classUnderTest = ToggleFlashlightUseCase(flashlightRepository)
    }

    @Test
    fun `invoke returns true when toggle succeeds`() = runTest {
        test(
            given = {
                everySuspend { flashlightRepository.toggle() } returns true
            },
            whenAction = {
                classUnderTest()
            },
            then = { result ->
                assertEquals(true, result)
                verifySuspend { flashlightRepository.toggle() }
            }
        )
    }

    @Test
    fun `invoke returns false when toggle fails`() = runTest {
        test(
            given = {
                everySuspend { flashlightRepository.toggle() } returns false
            },
            whenAction = {
                classUnderTest()
            },
            then = { result ->
                assertEquals(false, result)
            }
        )
    }
}
