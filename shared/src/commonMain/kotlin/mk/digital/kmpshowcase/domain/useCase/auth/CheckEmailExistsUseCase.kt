package mk.digital.kmpshowcase.domain.useCase.auth

import mk.digital.kmpshowcase.domain.repository.AuthRepository
import mk.digital.kmpshowcase.domain.useCase.base.UseCase

class CheckEmailExistsUseCase(
    private val authRepository: AuthRepository
) : UseCase<String, Boolean>() {
    override suspend fun run(params: String): Boolean = authRepository.emailExists(params)
}
