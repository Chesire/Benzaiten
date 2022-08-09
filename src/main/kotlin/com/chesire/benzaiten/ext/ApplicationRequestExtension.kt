package com.chesire.benzaiten.ext

import com.chesire.benzaiten.Const
import io.ktor.server.request.ApplicationRequest

/**
 * Retrieves the spotify auth token from the headers.
 */
val ApplicationRequest.spotifyToken: String?
    get() = headers[Const.SPOTIFY_TOKEN]
