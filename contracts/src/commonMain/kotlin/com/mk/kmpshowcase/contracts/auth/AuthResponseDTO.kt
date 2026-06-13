package com.mk.kmpshowcase.contracts.auth

import kotlinx.serialization.Serializable

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
