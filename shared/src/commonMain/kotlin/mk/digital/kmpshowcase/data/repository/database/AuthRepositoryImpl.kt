package mk.digital.kmpshowcase.data.repository.database

import kotlin.time.Clock
import mk.digital.kmpshowcase.data.database.AppDatabase
import mk.digital.kmpshowcase.domain.model.RegisteredUser
import mk.digital.kmpshowcase.domain.repository.AuthRepository

class AuthRepositoryImpl(
    database: AppDatabase
) : AuthRepository {

    private val queries = database.registeredUserQueries

    override suspend fun register(name: String, email: String, password: String): RegisteredUser {
        val createdAt = Clock.System.now().toEpochMilliseconds()
        queries.insert(name, email, password, createdAt)
        return queries.selectByEmail(email).executeAsOne().transform()
    }

    override suspend fun login(email: String, password: String): RegisteredUser? {
        val user = queries.selectByEmail(email).executeAsOneOrNull()?.transform()
        return if (user?.password == password) user else null
    }

    override suspend fun getUserByEmail(email: String): RegisteredUser? =
        queries.selectByEmail(email).executeAsOneOrNull()?.transform()

    override suspend fun emailExists(email: String): Boolean =
        queries.selectByEmail(email).executeAsOneOrNull() != null
}
