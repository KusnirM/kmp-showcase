package mk.digital.kmpshowcase.data.repository.database

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import mk.digital.kmpshowcase.data.database.AppDatabase
import mk.digital.kmpshowcase.domain.model.Note
import mk.digital.kmpshowcase.domain.repository.NoteRepository

class NoteRepositoryImpl(
    private val database: AppDatabase
) : NoteRepository {

    private val queries get() = database.noteQueries

    override fun observeAll(): Flow<List<Note>> {
        return queries.selectAll()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { it.toDomain() }
    }

    override suspend fun getById(id: Long): Note? = withContext(Dispatchers.IO) {
        queries.selectById(id).executeAsOneOrNull()?.toDomain()
    }

    override suspend fun insert(note: Note) = withContext(Dispatchers.IO) {
        queries.insert(note.title, note.content, note.createdAt)
    }

    override suspend fun update(note: Note) = withContext(Dispatchers.IO) {
        queries.update(note.title, note.content, note.id)
    }

    override suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        queries.deleteById(id)
    }

    override suspend fun deleteAll() = withContext(Dispatchers.IO) {
        queries.deleteAll()
    }

    override suspend fun count(): Long = withContext(Dispatchers.IO) {
        queries.count().executeAsOne()
    }
}
