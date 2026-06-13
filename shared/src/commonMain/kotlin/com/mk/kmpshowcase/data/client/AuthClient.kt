package com.mk.kmpshowcase.data.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import com.mk.kmpshowcase.data.dto.AuthResponseDTO
import com.mk.kmpshowcase.data.dto.LoginRequestDTO
import com.mk.kmpshowcase.data.dto.RegisterRequestDTO
import com.mk.kmpshowcase.data.network.handleApiCall

interface AuthClient {
    suspend fun login(email: String, password: String): AuthResponseDTO
    suspend fun register(email: String, password: String, name: String): AuthResponseDTO
}

class AuthClientImpl(
    private val client: HttpClient
) : AuthClient {

    override suspend fun login(email: String, password: String): AuthResponseDTO = handleApiCall {
        client.post("api/auth/login") {
            setBody(LoginRequestDTO(email, password))
        }.body()
    }

    override suspend fun register(email: String, password: String, name: String): AuthResponseDTO = handleApiCall {
        client.post("api/auth/register") {
            setBody(RegisterRequestDTO(email, password, name))
        }.body()
    }
}
