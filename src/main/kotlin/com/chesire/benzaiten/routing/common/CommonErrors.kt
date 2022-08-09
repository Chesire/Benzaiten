package com.chesire.benzaiten.routing.common

import com.chesire.benzaiten.routing.ErrorDomain

/**
 * Provides an [ErrorDomain] for if the spotify token is invalid.
 */
val noTokenError = ErrorDomain(
    "No spotify token",
    "Please provide a spotify token in the headers."
)
