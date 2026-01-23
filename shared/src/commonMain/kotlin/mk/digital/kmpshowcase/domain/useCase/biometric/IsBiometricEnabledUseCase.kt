package mk.digital.kmpshowcase.domain.useCase.biometric

import mk.digital.kmpshowcase.domain.repository.BiometricRepository
import mk.digital.kmpshowcase.domain.useCase.base.None
import mk.digital.kmpshowcase.domain.useCase.base.UseCase

class IsBiometricEnabledUseCase(
    private val biometricRepository: BiometricRepository
) : UseCase<None, Boolean>() {
    override suspend fun run(params: None): Boolean = biometricRepository.enabled()
}
