package com.chesire.benzaiten.service.playlist

import com.chesire.benzaiten.service.profile.ExternalUrls
import com.chesire.benzaiten.service.profile.Image
import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class SpotifyPlaylistDto(
    @SerialName("href")
    val href: String,
    @SerialName("items")
    val items: List<SpotifyPlaylistDetails>,
    @SerialName("limit")
    val limit: Int,
    @SerialName("next")
    val next: String,
    @SerialName("offset")
    val offset: Int,
    @SerialName("previous")
    val previous: String,
    @SerialName("total")
    val total: Int
)

@kotlinx.serialization.Serializable
data class SpotifyPlaylistDetails(
    @SerialName("collaborative")
    val collaborative: Boolean,
    @SerialName("description")
    val description: String,
    @SerialName("externalUrls")
    val externalUrls: ExternalUrls, // TODO: Make this generic
    @SerialName("href")
    val href: String,
    @SerialName("id")
    val id: String,
    @SerialName("images")
    val images: List<Image>, // TODO: Make this generic
    @SerialName("name")
    val name: String,
    @SerialName("owner")
    val owner: SpotifyPlaylistOwner,
    @SerialName("primaryColor")
    val primaryColor: String?, // TODO: Not sure what this can be
    @SerialName("public")
    val public: Boolean,
    @SerialName("snapshotId")
    val snapshotId: String,
    @SerialName("tracks")
    val tracks: SpotifyPlaylistTracks,
    @SerialName("type")
    val type: String,
    @SerialName("uri")
    val uri: String
)

@kotlinx.serialization.Serializable
data class SpotifyPlaylistOwner(
    @SerialName("displayName")
    val displayName: String,
    @SerialName("externalUrls")
    val externalUrls: ExternalUrls,
    @SerialName("href")
    val href: String,
    @SerialName("id")
    val id: String,
    @SerialName("type")
    val type: String,
    @SerialName("uri")
    val uri: String
)

@kotlinx.serialization.Serializable
data class SpotifyPlaylistTracks(
    @SerialName("href")
    val href: String,
    @SerialName("total")
    val total: Int
)
