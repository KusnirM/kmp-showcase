package com.mk.kmpshowcase.server.routes

import com.mk.kmpshowcase.server.model.AuthResponse
import com.mk.kmpshowcase.server.model.LoginRequest
import com.mk.kmpshowcase.server.model.RegisterRequest
import com.mk.kmpshowcase.server.plugins.JwtConfig
import com.mk.kmpshowcase.server.repository.UserRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("AuthRoutes")

fun Route.authRoutes() {
    val userRepository = UserRepository()

    route("/api/auth") {
        post("/register") {
            val request = call.receive<RegisterRequest>()

            require(request.email.contains("@")) { "Invalid email format" }
            require(request.password.length >= 8) { "Password must be at least 8 characters" }
            require(request.name.isNotBlank()) { "Name cannot be blank" }

            if (userRepository.findByEmail(request.email) != null) {
                logger.warn("Registration failed: email already exists - ${request.email}")
                call.respond(HttpStatusCode.Conflict, mapOf("message" to "User already exists"))
                return@post
            }

            val user = userRepository.create(request.email, request.password, request.name)
            val token = JwtConfig.generateToken(user.id, user.email)

            logger.info("User registered: ${user.id} (${user.email})")
            call.respond(HttpStatusCode.Created, AuthResponse(token, user))
        }

        post("/login") {
            val request = call.receive<LoginRequest>()

            val user = userRepository.findByEmail(request.email)
            if (user == null || !userRepository.verifyPassword(request.email, request.password)) {
                logger.warn("Login failed: invalid credentials for ${request.email}")
                call.respond(HttpStatusCode.Unauthorized, mapOf("message" to "Invalid credentials"))
                return@post
            }

            val token = JwtConfig.generateToken(user.id, user.email)
            logger.info("User logged in: ${user.id} (${user.email})")
            call.respond(AuthResponse(token, user))
        }
    }
}
