package com.mk.kmpshowcase.server2

import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("Application")

private const val DEFAULT_PORT = 8080

fun main() {
    embeddedServer(
        factory = Netty,
        port = System.getenv("PORT")?.toIntOrNull() ?: DEFAULT_PORT,
        host = "0.0.0.0",
        module = Application::module,
    ).start(wait = true)
}

@Suppress("unused")
private fun Application.module() {
    logger.info("Server starting...")

    // TODO Step 1: DatabaseConfig.init()
    // TODO Step 2: configureCallLogging()
    // TODO Step 3: configureSerialization()
    // TODO Step 4: configureStatusPages()
    // TODO Step 5: configureCORS()
    // TODO Step 6: configureAuth()
    // TODO Step 7: val dependencies = AppDependencies(); configureRouting(dependencies)
}
