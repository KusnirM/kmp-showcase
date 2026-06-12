package com.mk.kmpshowcase.server.feature.note.api

import com.mk.kmpshowcase.server.feature.note.service.Note
import kotlinx.serialization.Serializable

@Serializable
internal data class NoteDTO(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: Long,
)

@Serializable
internal data class CreateNoteRequest(
    val title: String,
    val content: String,
)

@Serializable
internal data class UpdateNoteRequest(
    val title: String,
    val content: String,
)

internal fun Note.toDTO() = NoteDTO(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt,
)
