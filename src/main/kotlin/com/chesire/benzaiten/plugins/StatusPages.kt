package com.chesire.benzaiten.plugins

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond

/**
 * Installs and configures the [StatusPages] plugin.
 */
fun Application.configureStatusPages() {
    install(StatusPages) {
        exception<Throwable> { call, _ ->
            call.respond(HttpStatusCode.InternalServerError)
        }
    }
}
