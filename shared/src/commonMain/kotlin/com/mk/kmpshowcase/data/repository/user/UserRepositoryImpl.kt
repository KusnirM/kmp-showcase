package com.mk.kmpshowcase.data.repository.user

import com.mk.kmpshowcase.data.base.transformAll
import com.mk.kmpshowcase.domain.model.User
import com.mk.kmpshowcase.domain.repository.UserRepository

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
