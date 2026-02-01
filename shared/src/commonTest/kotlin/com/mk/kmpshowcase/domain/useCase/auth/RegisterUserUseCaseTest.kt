package com.mk.kmpshowcase.domain.useCase.auth

import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kotlinx.coroutines.test.runTest
import com.mk.kmpshowcase.domain.BaseTest
import com.mk.kmpshowcase.domain.model.RegisteredUser
import com.mk.kmpshowcase.domain.repository.AuthRepository
import com.mk.kmpshowcase.domain.test
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RegisterUserUseCaseTest : BaseTest<RegisterUserUseCase>() {
    override lateinit var classUnderTest: RegisterUserUseCase

    private val authRepository: AuthRepository = mock()

    override fun beforeEach() {
        classUnderTest = RegisterUserUseCase(authRepository)
    }

    @Test
    fun `invoke registers user and returns registered user`() = runTest {
        val name = "John Doe"
        val email = "john@example.com"
        val password = "Test123!"
        val expectedUser = RegisteredUser(
            id = 1,
            name = name,
            email = email,
            password = password,
            createdAt = 1234567890L
        )

        test(
            given = {
                everySuspend { authRepository.register(name, email, password) } returns expectedUser
            },
            whenAction = {
                classUnderTest(RegisterUserUseCase.Params(name, email, password))
            },
            then = { result ->
                assertEquals(expectedUser, result)
                verifySuspend { authRepository.register(name, email, password) }
            }
        )
    }

    @Test
    fun `invoke throws exception when registration fails`() = runTest {
        val name = "John Doe"
        val email = "john@example.com"
        val password = "Test123!"
        val exception = RuntimeException("Registration failed")

        everySuspend { authRepository.register(name, email, password) } throws exception

        assertFailsWith<RuntimeException> {
            classUnderTest(RegisterUserUseCase.Params(name, email, password))
        }
    }
}
