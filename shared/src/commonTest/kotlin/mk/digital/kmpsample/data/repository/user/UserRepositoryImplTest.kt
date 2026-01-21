package mk.digital.kmpsample.data.repository.user

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import kotlinx.coroutines.test.runTest
import mk.digital.kmpsample.data.dto.AddressDTO
import mk.digital.kmpsample.data.dto.UserDTO
import mk.digital.kmpsample.domain.BaseTest
import mk.digital.kmpsample.domain.model.Address
import mk.digital.kmpsample.domain.model.User
import mk.digital.kmpsample.domain.test
import kotlin.test.Test
import kotlin.test.assertEquals

class UserRepositoryImplTest : BaseTest<UserRepositoryImpl>() {
    override lateinit var classUnderTest: UserRepositoryImpl

    private val client: UserClient = mock()

    override fun beforeEach() {
        classUnderTest = UserRepositoryImpl(client)
    }

    @Test
    fun getUser() = runTest {
        val id = 1
        val dto = UserDTO(
            address = AddressDTO(city = "city", street = "street", suite = "suite", zipcode = "zipcode"),
            email = "email",
            id = 1,
            name = "name"
        )
        val user = User(
            address = Address(city = "city", street = "street", suite = "suite", zipcode = "zipcode"),
            email = "email",
            id = 1,
            name = "name"
        )

        test(
            given = {
                everySuspend { client.fetchUser(id) } returns dto
            },
            whenAction = {
                classUnderTest.getUser(id)
            },
            then = {
                assertEquals(user, it)
            }
        )
    }

    @Test
    fun getUsers() = runTest {
        val dto = UserDTO(
            address = AddressDTO(city = "city", street = "street", suite = "suite", zipcode = "zipcode"),
            email = "email",
            id = 1,
            name = "name"
        )
        val user = User(
            Address(city = "city", street = "street", suite = "suite", zipcode = "zipcode"),
            email = "email",
            id = 1,
            name = "name"
        )
        val dtos = listOf(dto)
        val users = listOf(user)

        test(
            given = {
                everySuspend { client.fetchUsers() } returns dtos
            },
            whenAction = {
                classUnderTest.getUsers()
            },
            then = {
                assertEquals(users, it)
            }
        )
    }
}
