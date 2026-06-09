package com.mk.kmpshowcase.server2.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("DatabaseConfig")

object DatabaseConfig {


    fun init() {
        val database = Database.connect(hikari())

    }

    private fun hikari(): HikariDataSource {
        val useH2 = System.getenv("USE_H2")?.toBoolean() ?: true

        val config = HikariConfig().apply {
            if (useH2) {
                logger.info("Using H2 in-memory database (development)")
                driverClassName = "org.h2.Driver"
                jdbcUrl = "jdbc:h2:mem:kmpshowcase;DB_CLOSE_DELAY=-1"
                username = "sa"
                password = ""
            } else {
                logger.info("Using PostgreSQL database (production)")
                driverClassName = "org.postgresql.Driver"
                jdbcUrl = System.getenv("DATABASE_URL")
                username = System.getenv("DATABASE_USER")
                password = System.getenv("DATABASE_PASSWORD")
            }
            maximumPoolSize = 10
        }
        return HikariDataSource(config)
    }
}