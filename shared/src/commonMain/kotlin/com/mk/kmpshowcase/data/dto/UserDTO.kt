package com.mk.kmpshowcase.data.dto

import kotlinx.serialization.Serializable
import com.mk.kmpshowcase.data.base.TransformToDomainModel
import com.mk.kmpshowcase.domain.model.User

@Serializable
data class UserDTO(
    val address: AddressDTO,
    val email: String,
    val id: Int,
    val name: String,
) : TransformToDomainModel<User> {
    override fun transform(): User = User(address = address.transform(), email = email, id = id, name = name)
}
