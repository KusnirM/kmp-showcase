package mk.digital.kmpshowcase.domain.useCase.biometric

import mk.digital.kmpshowcase.data.biometric.BiometricResult
import mk.digital.kmpshowcase.domain.repository.BiometricRepository
import mk.digital.kmpshowcase.domain.useCase.base.None
import mk.digital.kmpshowcase.domain.useCase.base.UseCase

class AuthenticateWithBiometricUseCase(
    private val biometricRepository: BiometricRepository
) : UseCase<None, BiometricResult>() {
    override suspend fun run(params: None): BiometricResult = biometricRepository.authenticate()
}
