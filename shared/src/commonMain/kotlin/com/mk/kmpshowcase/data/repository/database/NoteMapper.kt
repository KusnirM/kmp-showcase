package com.mk.kmpshowcase.data.repository.database

import com.mk.kmpshowcase.domain.model.Note
import com.mk.kmpshowcase.data.database.Note as NoteEntity

fun NoteEntity.transform() = Note(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt
)

fun List<NoteEntity>.transformAll() = map(NoteEntity::transform)
