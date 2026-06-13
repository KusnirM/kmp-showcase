package com.mk.kmpshowcase.server.feature.user.api

import com.mk.kmpshowcase.contracts.user.UserResponseDTO
import com.mk.kmpshowcase.server.feature.user.service.User

internal fun User.toUserResponseDTO() = UserResponseDTO(
    id = id,
    email = email,
    name = name,
    createdAt = createdAt,
)
