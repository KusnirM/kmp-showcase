package com.mk.kmpshowcase.server.feature.admin.api

import com.mk.kmpshowcase.server.feature.lead.service.Lead
import kotlinx.serialization.Serializable

@Serializable
internal data class AdminLeadDTO(
    val id: Long,
    val email: String,
    val appType: String,
    val platforms: List<String>,
    val features: List<String>,
    val name: String?,
    val phone: String?,
    val note: String?,
    val hasDoc: Boolean,
    val createdAt: Long,
)

internal fun Lead.toAdminLeadDTO() = AdminLeadDTO(
    id = id,
    email = email,
    appType = appType,
    platforms = platforms,
    features = features,
    name = name,
    phone = phone,
    note = note,
    hasDoc = hasDoc,
    createdAt = createdAt,
)
