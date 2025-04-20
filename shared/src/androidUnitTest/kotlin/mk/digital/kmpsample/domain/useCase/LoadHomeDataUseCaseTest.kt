package mk.digital.kmpsample.domain.useCase

import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import mk.digital.kmpsample.domain.BaseTest
import mk.digital.kmpsample.domain.model.User
import mk.digital.kmpsample.domain.repository.UserRepository
import mk.digital.kmpsample.domain.test
import mk.digital.kmpsample.domain.useCase.base.invoke
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


class LoadHomeDataUseCaseTest : BaseTest<LoadHomeDataUseCase>() {
    override lateinit var classUnderTest: LoadHomeDataUseCase

    @MockK
    private lateinit var userRepository: UserRepository

    override fun beforeEach() {
        classUnderTest = LoadHomeDataUseCase(userRepository)
    }

    @Test
    fun testSuccess() = runTest {
        val users = mockk<List<User>>()

        test(
            given = {
                coEvery { userRepository.getUsers() } returns users
            },
            whenAction = {
                classUnderTest()
            },
            then = {
                Assertions.assertEquals(users, it)
            }
        )
    }
}