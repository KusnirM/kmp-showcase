package mk.digital.kmpsample.domain.model

data class User(
    val address: Address,
    val email: String,
    val id: Int,
    val name: String,
)
