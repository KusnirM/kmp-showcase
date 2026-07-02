package com.mk.kmpshowcase.server.feature.admin.api

import com.mk.kmpshowcase.contracts.ApiVersion
import com.mk.kmpshowcase.server.core.auth.isAdmin
import com.mk.kmpshowcase.server.feature.lead.service.LeadService
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route

// Admin-only. Role is enforced server-side per endpoint — a non-admin token never reaches the data.
internal fun Route.adminRoutes(leadService: LeadService) {
    route("${ApiVersion.BASE}/admin") {
        authenticate("auth-jwt") {
            get("/leads") {
                if (!call.isAdmin()) return@get call.respond(HttpStatusCode.Forbidden)
                call.respond(leadService.getAll().map { it.toAdminLeadDTO() })
            }
        }
    }
}
