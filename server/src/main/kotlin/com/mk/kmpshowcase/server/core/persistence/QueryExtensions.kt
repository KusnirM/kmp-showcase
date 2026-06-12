package com.mk.kmpshowcase.server.core.persistence

import org.jetbrains.exposed.sql.Query
import org.jetbrains.exposed.sql.ResultRow

internal fun <T> Query.mapToSingleOrNull(transform: (ResultRow) -> T): T? =
    map(transform).singleOrNull()
