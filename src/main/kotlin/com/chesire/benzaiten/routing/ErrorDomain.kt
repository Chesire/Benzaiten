package com.chesire.benzaiten.routing

import kotlinx.serialization.SerialName

/**
 * Domain for any errors that come out from this API.
 */
@kotlinx.serialization.Serializable
data class ErrorDomain(
    @SerialName("error")
    val error: String,
    @SerialName("error_description")
    val errorDescription: String
)
