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
    val collaborative: Boolean,
    val description: String,
    val externalUrls: ExternalUrls, // TODO: Make this generic
    val href: String,
    val id: String,
    val images: List<Image>, // TODO: Make this generic
    val name: String,
    val owner: SpotifyPlaylistOwner,
    val primaryColor: String?, // TODO: Not sure what this can be
    val public: Boolean,
    val snapshotId: String,
    val tracks: SpotifyPlaylistTracks,
    val type: String,
    val uri: String
)

@kotlinx.serialization.Serializable
data class SpotifyPlaylistOwner(
    val displayName: String,
    val externalUrls: ExternalUrls,
    val href: String,
    val id: String,
    val type: String,
    val uri: String
)

@kotlinx.serialization.Serializable
data class SpotifyPlaylistTracks(
    val href: String,
    val total: Int
)
