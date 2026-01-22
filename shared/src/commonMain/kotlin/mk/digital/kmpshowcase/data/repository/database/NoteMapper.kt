package mk.digital.kmpshowcase.data.repository.database

import mk.digital.kmpshowcase.domain.model.Note
import mk.digital.kmpshowcase.data.database.Note as NoteEntity

fun NoteEntity.transform() = Note(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt
)

fun List<NoteEntity>.transformAll() = map(NoteEntity::transform)
