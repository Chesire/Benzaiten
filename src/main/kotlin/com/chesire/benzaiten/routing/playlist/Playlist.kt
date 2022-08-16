package com.chesire.benzaiten.routing.playlist

import com.chesire.benzaiten.ext.spotifyToken
import com.chesire.benzaiten.routing.noTokenError
import com.chesire.benzaiten.service.playlist.PlaylistService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import org.koin.ktor.ext.inject

fun Routing.playlist() {
    val service by inject<PlaylistService>()

    get("playlist/") {
        val token = call.request.spotifyToken
        if (token == null) {
            call.response.status(HttpStatusCode.Unauthorized)
            call.respond(noTokenError)
            return@get
        }

        service.retrieveUserPlaylists(token)

    }
}
