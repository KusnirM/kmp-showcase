package mk.digital.kmpshowcase.domain.useCase.location

import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import mk.digital.kmpshowcase.domain.BaseTest
import mk.digital.kmpshowcase.domain.model.Location
import mk.digital.kmpshowcase.domain.repository.LocationRepository
import mk.digital.kmpshowcase.domain.test
import mk.digital.kmpshowcase.domain.useCase.base.invoke
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class GetLastKnownLocationUseCaseTest : BaseTest<GetLastKnownLocationUseCase>() {
    override lateinit var classUnderTest: GetLastKnownLocationUseCase

    private val locationRepository: LocationRepository = mock()

    override fun beforeEach() {
        classUnderTest = GetLastKnownLocationUseCase(locationRepository)
    }

    @Test
    fun `invoke returns location from repository`() = runTest {
        val expectedLocation = Location(lat = 48.1486, lon = 17.1077)

        test(
            given = {
                everySuspend { locationRepository.lastKnownLocation() } returns expectedLocation
            },
            whenAction = {
                classUnderTest()
            },
            then = { result ->
                assertEquals(expectedLocation, result)
                verifySuspend { locationRepository.lastKnownLocation() }
            }
        )
    }

    @Test
    fun `invoke throws exception when repository fails`() = runTest {
        val exception = RuntimeException("Location error")

        everySuspend { locationRepository.lastKnownLocation() } throws exception

        assertFailsWith<RuntimeException> {
            classUnderTest()
        }
    }
}
