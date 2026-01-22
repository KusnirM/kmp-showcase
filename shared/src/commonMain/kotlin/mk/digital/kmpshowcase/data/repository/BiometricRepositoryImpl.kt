package mk.digital.kmpshowcase.data.repository

import mk.digital.kmpshowcase.data.biometric.BiometricClient
import mk.digital.kmpshowcase.data.biometric.BiometricResult
import mk.digital.kmpshowcase.domain.repository.BiometricRepository

class BiometricRepositoryImpl(
    private val biometricClient: BiometricClient,
) : BiometricRepository {

    override fun enabled(): Boolean = biometricClient.enabled()

    override suspend fun authenticate(): BiometricResult = biometricClient.authenticate()
}
