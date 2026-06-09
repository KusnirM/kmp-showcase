package com.mk.kmpshowcase.server

import com.mk.kmpshowcase.server.config.DatabaseConfig
import com.mk.kmpshowcase.server.plugins.configureAuth
import com.mk.kmpshowcase.server.plugins.configureCallLogging
import com.mk.kmpshowcase.server.plugins.configureCORS
import com.mk.kmpshowcase.server.plugins.configureRouting
import com.mk.kmpshowcase.server.plugins.configureSerialization
import com.mk.kmpshowcase.server.plugins.configureStatusPages
import io.ktor.server.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("Application")

fun main() {
    embeddedServer(
        factory = Netty,
        port = System.getenv("PORT")?.toIntOrNull() ?: 8080,
        host = "0.0.0.0",
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    logger.info("Server starting...")
    DatabaseConfig.init()
    configureCallLogging()
    configureSerialization()
    configureStatusPages()
    configureCORS()
    configureAuth()
    configureRouting()
}
