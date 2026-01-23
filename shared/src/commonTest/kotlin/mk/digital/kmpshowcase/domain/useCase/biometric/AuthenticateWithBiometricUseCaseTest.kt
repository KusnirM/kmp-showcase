package mk.digital.kmpshowcase.domain.useCase.biometric

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import mk.digital.kmpshowcase.data.biometric.BiometricResult
import mk.digital.kmpshowcase.domain.BaseTest
import mk.digital.kmpshowcase.domain.repository.BiometricRepository
import mk.digital.kmpshowcase.domain.test
import mk.digital.kmpshowcase.domain.useCase.base.invoke
import kotlin.test.Test
import kotlin.test.assertEquals

class AuthenticateWithBiometricUseCaseTest : BaseTest<AuthenticateWithBiometricUseCase>() {
    override lateinit var classUnderTest: AuthenticateWithBiometricUseCase

    private val biometricRepository: BiometricRepository = mock()

    override fun beforeEach() {
        classUnderTest = AuthenticateWithBiometricUseCase(biometricRepository)
    }

    @Test
    fun `invoke returns Success when authentication succeeds`() = runTest {
        test(
            given = {
                everySuspend { biometricRepository.authenticate() } returns BiometricResult.Success
            },
            whenAction = {
                classUnderTest()
            },
            then = { result ->
                assertEquals(BiometricResult.Success, result)
                verifySuspend { biometricRepository.authenticate() }
            }
        )
    }

    @Test
    fun `invoke returns Cancelled when user cancels`() = runTest {
        test(
            given = {
                everySuspend { biometricRepository.authenticate() } returns BiometricResult.Cancelled
            },
            whenAction = {
                classUnderTest()
            },
            then = { result ->
                assertEquals(BiometricResult.Cancelled, result)
            }
        )
    }

    @Test
    fun `invoke returns NotAvailable when biometrics not available`() = runTest {
        test(
            given = {
                everySuspend { biometricRepository.authenticate() } returns BiometricResult.NotAvailable
            },
            whenAction = {
                classUnderTest()
            },
            then = { result ->
                assertEquals(BiometricResult.NotAvailable, result)
            }
        )
    }

    @Test
    fun `invoke returns SystemError when error occurs`() = runTest {
        val errorMessage = "System error"
        test(
            given = {
                everySuspend { biometricRepository.authenticate() } returns BiometricResult.SystemError(errorMessage)
            },
            whenAction = {
                classUnderTest()
            },
            then = { result ->
                assertEquals(BiometricResult.SystemError(errorMessage), result)
            }
        )
    }
}
