package com.mk.kmpshowcase.data.repository

import com.mk.kmpshowcase.data.biometric.BiometricClient
import com.mk.kmpshowcase.data.biometric.BiometricResult
import com.mk.kmpshowcase.domain.repository.BiometricRepository

class BiometricRepositoryImpl(
    private val biometricClient: BiometricClient,
) : BiometricRepository {

    override fun enabled(): Boolean = biometricClient.enabled()

    override suspend fun authenticate(): BiometricResult = biometricClient.authenticate()
}
