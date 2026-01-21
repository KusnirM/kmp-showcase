package mk.digital.kmpsample.data.dto

import kotlinx.serialization.Serializable
import mk.digital.kmpsample.data.base.TransformToDomainModel
import mk.digital.kmpsample.domain.model.User

@Serializable
data class UserDTO(
    val address: AddressDTO,
    val email: String,
    val id: Int,
    val name: String,
) : TransformToDomainModel<User> {
    override fun transform(): User = User(address = address.transform(), email = email, id = id, name = name)
}
