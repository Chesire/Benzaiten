package com.chesire.benzaiten.plugins

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation

/**
 * Configures the [ContentNegotiation] plugin.
 */
fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
}
