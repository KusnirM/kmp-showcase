package com.mk.kmpshowcase.data.repository

import com.mk.kmpshowcase.data.client.AuthClient
import com.mk.kmpshowcase.domain.model.AuthSession
import com.mk.kmpshowcase.domain.repository.AuthRepository

class AuthRepositoryImpl(
    private val client: AuthClient
) : AuthRepository {

    override suspend fun login(email: String, password: String): AuthSession {
        val response = client.login(email, password)
        return AuthSession(
            token = response.token,
            userId = response.user.id,
            email = response.user.email,
            name = response.user.name,
        )
    }

    override suspend fun register(name: String, email: String, password: String): AuthSession {
        val response = client.register(email, password, name)
        return AuthSession(
            token = response.token,
            userId = response.user.id,
            email = response.user.email,
            name = response.user.name,
        )
    }
}
