package com.mk.kmpshowcase.domain.useCase.auth

import com.mk.kmpshowcase.domain.repository.AuthRepository
import com.mk.kmpshowcase.domain.useCase.base.UseCase

class CheckEmailExistsUseCase(
    private val authRepository: AuthRepository
) : UseCase<String, Boolean>() {
    override suspend fun run(params: String): Boolean = authRepository.emailExists(params)
}
