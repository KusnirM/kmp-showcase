package com.mk.kmpshowcase.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDTO(
    val email: String,
    val password: String,
)

@Serializable
data class RegisterRequestDTO(
    val email: String,
    val password: String,
    val name: String,
)

@Serializable
data class AuthUserDTO(
    val id: Long,
    val email: String,
    val name: String,
)

@Serializable
data class AuthResponseDTO(
    val token: String,
    val user: AuthUserDTO,
)
