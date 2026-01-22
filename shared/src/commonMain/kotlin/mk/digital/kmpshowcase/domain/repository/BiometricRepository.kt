package mk.digital.kmpshowcase.domain.repository

import mk.digital.kmpshowcase.data.biometric.BiometricResult

interface BiometricRepository {
    fun enabled(): Boolean
    suspend fun authenticate(): BiometricResult
}
