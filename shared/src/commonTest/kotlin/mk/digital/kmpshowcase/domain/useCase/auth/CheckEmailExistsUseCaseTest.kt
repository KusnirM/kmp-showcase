package mk.digital.kmpshowcase.domain.useCase.auth

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import mk.digital.kmpshowcase.domain.BaseTest
import mk.digital.kmpshowcase.domain.repository.AuthRepository
import mk.digital.kmpshowcase.domain.test
import kotlin.test.Test
import kotlin.test.assertEquals

class CheckEmailExistsUseCaseTest : BaseTest<CheckEmailExistsUseCase>() {
    override lateinit var classUnderTest: CheckEmailExistsUseCase

    private val authRepository: AuthRepository = mock()

    override fun beforeEach() {
        classUnderTest = CheckEmailExistsUseCase(authRepository)
    }

    @Test
    fun `invoke returns true when email exists`() = runTest {
        val email = "test@example.com"
        test(
            given = {
                everySuspend { authRepository.emailExists(email) } returns true
            },
            whenAction = {
                classUnderTest(email)
            },
            then = { result ->
                assertEquals(true, result)
                verifySuspend { authRepository.emailExists(email) }
            }
        )
    }

    @Test
    fun `invoke returns false when email does not exist`() = runTest {
        val email = "new@example.com"
        test(
            given = {
                everySuspend { authRepository.emailExists(email) } returns false
            },
            whenAction = {
                classUnderTest(email)
            },
            then = { result ->
                assertEquals(false, result)
            }
        )
    }
}
