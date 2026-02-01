package com.mk.kmpshowcase.data.biometric

interface BiometricClient {
    fun enabled(): Boolean
    suspend fun authenticate(): BiometricResult
}

expect class BiometricClientImpl: BiometricClient {
    override fun enabled(): Boolean
    override suspend fun authenticate(): BiometricResult
}
