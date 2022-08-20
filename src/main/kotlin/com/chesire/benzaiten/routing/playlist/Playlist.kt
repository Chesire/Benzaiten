package com.chesire.benzaiten.routing.playlist

import com.chesire.benzaiten.ext.spotifyToken
import com.chesire.benzaiten.routing.ErrorDomain
import com.chesire.benzaiten.routing.missingUsername
import com.chesire.benzaiten.routing.noTokenError
import com.chesire.benzaiten.service.playlist.PlaylistService
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import org.koin.ktor.ext.inject

/**
 * Route to access details about users playlists.
 */
fun Routing.playlist() {
    val service by inject<PlaylistService>()

    /**
     * Get the current users playlists.
     */
    get("playlist/") {
        val token = call.request.spotifyToken
        if (token == null) {
            call.response.status(HttpStatusCode.Unauthorized)
            call.respond(noTokenError)
            return@get
        }

        service.retrieveMyPlaylists(token)
            .onSuccess { call.respond(it) }
            .onFailure {
                call.response.status(HttpStatusCode.fromValue(it.details.status))
                call.respond(
                    ErrorDomain(
                        it.details.status.toString(),
                        it.details.message
                    )
                )
            }
    }
    /**
     * Get the playlists for the provided username.
     */
    get("playlist/{username}/") {
        val token = call.request.spotifyToken
        if (token == null) {
            call.response.status(HttpStatusCode.Unauthorized)
            call.respond(noTokenError)
            return@get
        }
        val username = call.parameters["username"]
        if (username == null) {
            call.response.status(HttpStatusCode.BadRequest) // TODO: Different code?
            call.respond(missingUsername)
            return@get
        }

        service.retrieveUserPlaylists(token, username)
            .onSuccess { call.respond(it) }
            .onFailure {
                call.response.status(HttpStatusCode.fromValue(it.details.status))
                call.respond(
                    ErrorDomain(
                        it.details.status.toString(),
                        it.details.message
                    )
                )
            }
    }
}
