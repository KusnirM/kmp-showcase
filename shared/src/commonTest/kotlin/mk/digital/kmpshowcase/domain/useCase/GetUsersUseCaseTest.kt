package mk.digital.kmpshowcase.domain.useCase

import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.mock
import kotlinx.coroutines.test.runTest
import mk.digital.kmpshowcase.domain.BaseTest
import mk.digital.kmpshowcase.domain.model.Address
import mk.digital.kmpshowcase.domain.model.User
import mk.digital.kmpshowcase.domain.repository.UserRepository
import mk.digital.kmpshowcase.domain.test
import mk.digital.kmpshowcase.domain.useCase.base.invoke
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class GetUsersUseCaseTest : BaseTest<GetUsersUseCase>() {
    override lateinit var classUnderTest: GetUsersUseCase

    private val userRepository: UserRepository = mock()

    override fun beforeEach() {
        classUnderTest = GetUsersUseCase(userRepository)
    }

    @Test
    fun `invoke returns list of users from repository`() = runTest {
        val users = listOf(
            User(
                address = Address(city = "New York", street = "5th Avenue", suite = "Apt 1", zipcode = "10001"),
                email = "john@example.com",
                id = 1,
                name = "John Doe"
            ),
            User(
                address = Address(city = "Los Angeles", street = "Sunset Blvd", suite = "Suite 200", zipcode = "90001"),
                email = "jane@example.com",
                id = 2,
                name = "Jane Smith"
            )
        )

        test(
            given = {
                everySuspend { userRepository.getUsers() } returns users
            },
            whenAction = {
                classUnderTest()
            },
            then = {
                assertEquals(users, it)
                assertEquals(2, it.size)
                assertEquals("John Doe", it[0].name)
                assertEquals("Jane Smith", it[1].name)
            }
        )
    }

    @Test
    fun `invoke returns empty list when no users`() = runTest {
        test(
            given = {
                everySuspend { userRepository.getUsers() } returns emptyList()
            },
            whenAction = {
                classUnderTest()
            },
            then = {
                assertTrue(it.isEmpty())
            }
        )
    }

    @Test
    fun `invoke throws exception when repository fails`() = runTest {
        val exception = RuntimeException("Network error")

        everySuspend { userRepository.getUsers() } throws exception

        assertFailsWith<RuntimeException> {
            classUnderTest()
        }
    }
}
