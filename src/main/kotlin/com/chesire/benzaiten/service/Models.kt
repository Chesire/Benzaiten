package com.chesire.benzaiten.service

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class Image(
    @SerialName("height")
    val height: Int?,
    @SerialName("url")
    val url: String,
    @SerialName("width")
    val width: Int?
)

@kotlinx.serialization.Serializable
data class ExternalUrls(
    @SerialName("spotify")
    val spotify: String
)
