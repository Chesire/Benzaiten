package com.chesire.benzaiten.service.playlist

import com.chesire.benzaiten.Const
import com.chesire.benzaiten.ext.toResult
import com.chesire.benzaiten.service.SpotifyErrorDto
import com.github.michaelbull.result.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.ContentType

private const val MY_PLAYLIST_URL = "/me/playlists"
private fun createPlaylistUrl(userId: String): String {
    return "${Const.SPOTIFY_BASE_URL}/users/$userId/playlists"
}

class PlaylistService(private val httpClient: HttpClient) {

    suspend fun retrieveMyPlaylists(bearerToken: String): Result<SpotifyPlaylistDto, SpotifyErrorDto> {
        return httpClient
            .get(MY_PLAYLIST_URL) {
                accept(ContentType.Application.Json)
                bearerAuth(bearerToken)
            }
            .toResult()
    }

    suspend fun retrieveUserPlaylists(
        bearerToken: String,
        username: String
    ): Result<SpotifyPlaylistDto, SpotifyErrorDto> {
        val playlistUrl = createPlaylistUrl(username)

        return httpClient
            .get(playlistUrl) {
                accept(ContentType.Application.Json)
                bearerAuth(bearerToken)
            }
            .toResult()
    }
}
