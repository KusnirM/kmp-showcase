package mk.digital.kmpsample.data.repository.user

import mk.digital.kmpsample.data.base.transformAll
import mk.digital.kmpsample.domain.model.User
import mk.digital.kmpsample.domain.repository.UserRepository

class UserRepositoryImpl(
    private val client: UserClient
) : UserRepository {
    override suspend fun getUser(id: Int): User {
        return client.fetchUser(id).transform()
    }

    override suspend fun getUsers(): List<User> {
        return client.fetchUsers().transformAll()
    }
}
