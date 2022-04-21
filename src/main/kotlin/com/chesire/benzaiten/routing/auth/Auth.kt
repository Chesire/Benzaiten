package com.chesire.benzaiten.routing.auth

import com.chesire.benzaiten.routing.auth.service.AuthService
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
}
