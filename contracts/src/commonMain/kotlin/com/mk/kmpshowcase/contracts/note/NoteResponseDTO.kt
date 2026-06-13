package com.mk.kmpshowcase.contracts.note

import kotlinx.serialization.Serializable

@Serializable
data class NoteResponseDTO(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: Long,
)
