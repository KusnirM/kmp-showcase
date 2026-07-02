package com.mk.kmpshowcase.server.feature.lead.persistence

import com.mk.kmpshowcase.server.feature.lead.service.Lead
import com.mk.kmpshowcase.server.feature.lead.service.LeadDraft
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

internal class LeadRepositoryImpl : LeadRepository {

    override suspend fun findAll(): List<Lead> = newSuspendedTransaction {
        LeadsTable.selectAll()
            .orderBy(LeadsTable.createdAt, SortOrder.DESC)
            .map { it.toLead() }
    }

    override suspend fun create(draft: LeadDraft): Lead = newSuspendedTransaction {
        val now = System.currentTimeMillis()
        val newId = LeadsTable.insert {
            it[email] = draft.email
            it[appType] = draft.appType
            it[platforms] = draft.platforms.joinToString(DELIMITER)
            it[features] = draft.features.joinToString(DELIMITER)
            it[name] = draft.name
            it[phone] = draft.phone
            it[note] = draft.note
            it[hasDoc] = draft.hasDoc
            it[createdAt] = now
        } get LeadsTable.id

        Lead(
            id = newId.value,
            email = draft.email,
            appType = draft.appType,
            platforms = draft.platforms,
            features = draft.features,
            name = draft.name,
            phone = draft.phone,
            note = draft.note,
            hasDoc = draft.hasDoc,
            createdAt = now,
        )
    }

    private fun ResultRow.toLead() = Lead(
        id = this[LeadsTable.id].value,
        email = this[LeadsTable.email],
        appType = this[LeadsTable.appType],
        platforms = this[LeadsTable.platforms].splitOrEmpty(),
        features = this[LeadsTable.features].splitOrEmpty(),
        name = this[LeadsTable.name],
        phone = this[LeadsTable.phone],
        note = this[LeadsTable.note],
        hasDoc = this[LeadsTable.hasDoc],
        createdAt = this[LeadsTable.createdAt],
    )

    private fun String.splitOrEmpty(): List<String> =
        if (isEmpty()) emptyList() else split(DELIMITER)

    private companion object {
        const val DELIMITER = "\n"
    }
}
