package com.mk.kmpshowcase.server.feature.note.api

import com.mk.kmpshowcase.contracts.ApiVersion
import com.mk.kmpshowcase.contracts.note.CreateNoteRequestDTO
import com.mk.kmpshowcase.contracts.note.UpdateNoteRequestDTO
import com.mk.kmpshowcase.server.core.auth.userId
import com.mk.kmpshowcase.server.feature.note.service.NoteService
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route

internal fun Route.noteRoutes(noteService: NoteService) {
    route("${ApiVersion.BASE}/notes") {
        authenticate("auth-jwt") {
            get {
                val userId = call.userId() ?: return@get call.respond(HttpStatusCode.Unauthorized)
                call.respond(noteService.listForUser(userId).map { it.toNoteResponseDTO() })
            }

            get("/search") {
                val userId = call.userId() ?: return@get call.respond(HttpStatusCode.Unauthorized)
                val query = call.request.queryParameters["q"]
                call.respond(noteService.listForUser(userId, query).map { it.toNoteResponseDTO() })
            }

            get("/{id}") {
                val userId = call.userId() ?: return@get call.respond(HttpStatusCode.Unauthorized)
                val noteId = call.parameters["id"]?.toLongOrNull()
                    ?: return@get call.respond(HttpStatusCode.BadRequest)
                val note = noteService.getById(noteId, userId)
                    ?: return@get call.respond(HttpStatusCode.NotFound)
                call.respond(note.toNoteResponseDTO())
            }

            post {
                val userId = call.userId() ?: return@post call.respond(HttpStatusCode.Unauthorized)
                val request = call.receive<CreateNoteRequestDTO>()
                val note = noteService.create(userId, request.title, request.content)
                call.respond(HttpStatusCode.Created, note.toNoteResponseDTO())
            }

            put("/{id}") {
                val userId = call.userId() ?: return@put call.respond(HttpStatusCode.Unauthorized)
                val noteId = call.parameters["id"]?.toLongOrNull()
                    ?: return@put call.respond(HttpStatusCode.BadRequest)
                val request = call.receive<UpdateNoteRequestDTO>()
                val note = noteService.update(noteId, userId, request.title, request.content)
                    ?: return@put call.respond(HttpStatusCode.NotFound)
                call.respond(note.toNoteResponseDTO())
            }

            delete("/{id}") {
                val userId = call.userId() ?: return@delete call.respond(HttpStatusCode.Unauthorized)
                val noteId = call.parameters["id"]?.toLongOrNull()
                    ?: return@delete call.respond(HttpStatusCode.BadRequest)
                if (noteService.delete(noteId, userId)) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }
    }
}
