package com.chesire.benzaiten.service.profile

import com.chesire.benzaiten.service.ExternalUrls
import com.chesire.benzaiten.service.Image
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class SpotifyProfileDto(
    @SerialName("country")
    val country: String,
    @SerialName("display_name")
    val displayName: String,
    @SerialName("email")
    val email: String,
    @SerialName("explicit_content")
    val explicitContent: ExplicitContent,
    @SerialName("external_urls")
    val externalUrls: ExternalUrls,
    @SerialName("followers")
    val followers: Followers,
    @SerialName("href")
    val href: String,
    @SerialName("id")
    val id: String,
    @SerialName("images")
    val images: List<Image>,
    @SerialName("product")
    val product: String,
    @SerialName("type")
    val type: String,
    @SerialName("uri")
    val uri: String
)

@kotlinx.serialization.Serializable
data class ExplicitContent(
    @SerialName("filter_enabled")
    val filterEnabled: Boolean,
    @SerialName("filter_locked")
    val filterLocked: Boolean
)

@kotlinx.serialization.Serializable
data class Followers(
    @SerialName("href")
    val href: String?,
    @SerialName("total")
    val total: Int
)
