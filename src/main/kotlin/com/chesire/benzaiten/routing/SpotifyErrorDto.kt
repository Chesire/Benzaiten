package com.chesire.benzaiten.routing

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class SpotifyErrorDto(
    @SerialName("error")
    val details: SpotifyErrorDtoDetails
)

@kotlinx.serialization.Serializable
data class SpotifyErrorDtoDetails(
    @SerialName("status")
    val status: Int,
    @SerialName("message")
    val message: String
)
