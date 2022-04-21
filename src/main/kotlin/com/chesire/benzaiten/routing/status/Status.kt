package com.chesire.benzaiten.routing.status

import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

/**
 * Route to get the status of the service.
 */
fun Route.status() {
    get("status/") {
        call.respondText("Benzaiten status OK!")
    }
}
