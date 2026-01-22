package mk.digital.kmpshowcase.data.biometric

interface BiometricClient {
    fun enabled(): Boolean
    suspend fun authenticate(): BiometricResult
}
