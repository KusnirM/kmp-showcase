package com.mk.kmpshowcase.domain.useCase.auth

import com.mk.kmpshowcase.domain.model.RegisteredUser
import com.mk.kmpshowcase.domain.repository.AuthRepository
import com.mk.kmpshowcase.domain.useCase.base.UseCase

class RegisterUserUseCase(
    private val authRepository: AuthRepository
) : UseCase<RegisterUserUseCase.Params, RegisteredUser>() {

    data class Params(
        val name: String,
        val email: String,
        val password: String
    )

    override suspend fun run(params: Params): RegisteredUser =
        authRepository.register(params.name, params.email, params.password)
}
