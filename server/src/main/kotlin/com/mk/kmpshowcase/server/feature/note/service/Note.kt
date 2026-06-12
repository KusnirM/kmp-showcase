package com.mk.kmpshowcase.server.feature.note.service

internal data class Note(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: Long,
)
