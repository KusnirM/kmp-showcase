package mk.digital.kmpsample.data.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

expect class HttpClientProvider() {
    fun create(): HttpClient
}

fun HttpClientConfig<*>.applyCommonConfig() {
    defaultRequest {
        url {
            protocol = URLProtocol.HTTPS
            host = "jsonplaceholder.typicode.com"
        }
    }
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        )
    }

    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }

    install(HttpTimeout) {
        requestTimeoutMillis = 30_000
        connectTimeoutMillis = 30_000
    }
}
