package com.chesire.benzaiten.routing.profile

import com.chesire.benzaiten.ext.spotifyToken
import com.chesire.benzaiten.routing.ErrorDomain
import com.chesire.benzaiten.routing.common.noTokenError
import com.chesire.benzaiten.routing.profile.service.ProfileService
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import org.koin.ktor.ext.inject

/**
 * Route to access details about a users profile.
 */
fun Route.profile() {
    val service by inject<ProfileService>()

    /**
     * Must have the spotify token in the headers.
     */
    get("profile/") {
        val token = call.request.spotifyToken
        if (token == null) {
            call.respond(noTokenError)
            return@get
        }

        service.retrieveUserProfile(token)
            .onSuccess { call.respondText { it.toString() } }
            .onFailure {
                call.respond(
                    ErrorDomain(
                        it.status.toString(),
                        it.message
                    )
                )
            }
    }
}