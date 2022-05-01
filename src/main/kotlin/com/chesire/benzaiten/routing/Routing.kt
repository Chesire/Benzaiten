package com.chesire.benzaiten.routing

import com.chesire.benzaiten.routing.auth.auth
import com.chesire.benzaiten.routing.status.status
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

/**
 * Configures and starts the Ktor routing.
 */
fun Application.configureRouting() {
    routing {
        auth()
        status()
    }
}
