package com.mk.kmpshowcase.data.dto

import com.mk.kmpshowcase.data.base.TransformToDomainModel
import com.mk.kmpshowcase.domain.model.AuthSession
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
): TransformToDomainModel<AuthSession> {
    override fun transform(): AuthSession = AuthSession(
        token = token,
        userId = user.id,
        email = user.email,
        name = user.name,
    )

}

