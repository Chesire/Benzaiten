package com.chesire.benzaiten.routing

/**
 * Provides an [ErrorDomain] for if the spotify token is invalid.
 */
val noTokenError = ErrorDomain(
    "No spotify token",
    "Please provide the spotify-token header."
)

/**
 * Provides an [ErrorDomain] for if the username is missing in a request.
 */
val missingUsername = ErrorDomain(
    "Missing username parameter",
    "Please provide username in the query."
)
