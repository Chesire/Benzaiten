package com.chesire.benzaiten.routing

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class SpotifyErrorDomain(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String
)
