package com.chesire.benzaiten.plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.application.log
import io.ktor.server.plugins.StatusPages
import io.ktor.server.response.respond

/**
 * Installs and configures the [StatusPages] plugin.
 */
fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            log.error("Global error - $cause")
            call.respond(HttpStatusCode.InternalServerError)
        }
    }
}
