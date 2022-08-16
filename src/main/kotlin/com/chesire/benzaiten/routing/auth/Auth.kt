package com.chesire.benzaiten.routing.auth

import com.chesire.benzaiten.service.auth.AuthService
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

/**
 * Route to use credentials to get an access token.
 */
fun Route.auth() {
    val authService = AuthService()

    get("auth/") {
        authService.performRequest()
    }
    get("authcallback/") {
        // Check webpage URL for token
        call.respondText { "OK" }
    }
}
