package mk.digital.kmpsample.data.repository.user

import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import mk.digital.kmpsample.data.dto.AddressDTO
import mk.digital.kmpsample.data.dto.UserDTO
import mk.digital.kmpsample.domain.BaseTest
import mk.digital.kmpsample.domain.model.Address
import mk.digital.kmpsample.domain.model.User
import mk.digital.kmpsample.domain.test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UserRepositoryImplTest : BaseTest<UserRepositoryImpl>() {
    override lateinit var classUnderTest: UserRepositoryImpl

    @MockK
    private lateinit var client: UserClient

    override fun beforeEach() {
        classUnderTest = UserRepositoryImpl(client)
    }

    @Test
    fun getUser() = runTest {
        val id = 1
        val dto = mockk<UserDTO>()
        val user = mockk<User>()
        test(
            given = {
                every { dto.transform() } returns user
                coEvery { client.fetchUser(id) } returns dto
            }, whenAction = { classUnderTest.getUser(id) },
            then = {
                Assertions.assertEquals(user, it)
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
                coEvery { client.fetchUsers() } returns dtos
            }, whenAction = { classUnderTest.getUsers() },
            then = {
                Assertions.assertEquals(users, it)
            }
        )
    }
}
