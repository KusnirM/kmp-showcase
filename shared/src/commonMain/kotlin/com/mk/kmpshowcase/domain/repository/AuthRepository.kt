package com.mk.kmpshowcase.domain.repository

import com.mk.kmpshowcase.domain.model.RegisteredUser

interface AuthRepository {
    suspend fun register(name: String, email: String, password: String): RegisteredUser
    suspend fun login(email: String, password: String): RegisteredUser?
    suspend fun getUserByEmail(email: String): RegisteredUser?
    suspend fun emailExists(email: String): Boolean
}
