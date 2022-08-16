package com.chesire.benzaiten.service.playlist

import com.chesire.benzaiten.Const
import com.chesire.benzaiten.service.SpotifyErrorDto
import com.github.michaelbull.result.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType

private fun createPlaylistUrl(userId: String): String {
    return "${Const.SPOTIFY_BASE_URL}/users/$userId/playlists"
}

class PlaylistService(private val httpClient: HttpClient) {

    suspend fun retrieveUserPlaylists(bearerToken: String): Result<SpotifyPlaylistDto, SpotifyErrorDto> {
        val userId = "username goes here" // TODO: Update this
        val playlistUrl = createPlaylistUrl(userId)


        val result = httpClient
            .get(playlistUrl) {
                accept(ContentType.Application.Json)
                bearerAuth(bearerToken)
            }

        val body = result.bodyAsText()


        error(body)
    }
}
