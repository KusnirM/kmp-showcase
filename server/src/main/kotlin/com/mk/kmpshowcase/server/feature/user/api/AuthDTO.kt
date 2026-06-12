package com.mk.kmpshowcase.server.feature.user.api

import kotlinx.serialization.Serializable

@Serializable
internal data class RegisterRequest(
    val email: String,
    val password: String,
    val name: String,
)

@Serializable
internal data class LoginRequest(
    val email: String,
    val password: String,
)

@Serializable
internal data class AuthResponse(
    val token: String,
    val user: UserDTO,
)
