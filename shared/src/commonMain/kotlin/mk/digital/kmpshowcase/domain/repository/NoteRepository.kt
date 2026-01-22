package mk.digital.kmpshowcase.domain.repository

import kotlinx.coroutines.flow.Flow
import mk.digital.kmpshowcase.domain.model.Note

interface NoteRepository {
    fun observeAll(): Flow<List<Note>>
    suspend fun getById(id: Long): Note?
    suspend fun insert(note: Note)
    suspend fun update(note: Note)
    suspend fun delete(id: Long)
    suspend fun deleteAll()
    suspend fun count(): Long
}
