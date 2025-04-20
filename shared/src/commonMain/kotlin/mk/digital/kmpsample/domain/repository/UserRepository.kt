package mk.digital.kmpsample.domain.repository

import mk.digital.kmpsample.domain.model.User

interface UserRepository {

    suspend fun getUser(id: Int): User

    suspend fun getUsers(): List<User>

}
