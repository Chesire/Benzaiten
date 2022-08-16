package com.chesire.benzaiten.routing

/**
 * Provides an [ErrorDomain] for if the spotify token is invalid.
 */
val noTokenError = ErrorDomain(
    "No spotify token",
    "Please provide the spotify-token header."
)
