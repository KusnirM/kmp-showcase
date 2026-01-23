package mk.digital.kmpshowcase.data.repository.database

import mk.digital.kmpshowcase.domain.model.RegisteredUser
import mk.digital.kmpshowcase.data.database.RegisteredUser as RegisteredUserEntity

fun RegisteredUserEntity.transform() = RegisteredUser(
    id = id,
    name = name,
    email = email,
    password = password,
    createdAt = createdAt
)
