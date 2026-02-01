package com.mk.kmpshowcase.domain.repository

import com.mk.kmpshowcase.data.biometric.BiometricResult

interface BiometricRepository {
    fun enabled(): Boolean
    suspend fun authenticate(): BiometricResult
}
