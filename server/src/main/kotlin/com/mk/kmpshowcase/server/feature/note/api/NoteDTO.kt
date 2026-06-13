package com.mk.kmpshowcase.server.feature.note.api

import com.mk.kmpshowcase.contracts.note.NoteResponseDTO
import com.mk.kmpshowcase.server.feature.note.service.Note

internal fun Note.toNoteResponseDTO() = NoteResponseDTO(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt,
)
