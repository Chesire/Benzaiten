package com.chesire.benzaiten.routing.profile.service

import com.chesire.benzaiten.ext.toResult
import com.chesire.benzaiten.routing.SpotifyErrorDto
import com.chesire.benzaiten.routing.profile.data.SpotifyProfileDto
import com.github.michaelbull.result.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.ContentType

private const val PROFILE_URL = "https://api.spotify.com/v1/me"

class ProfileService(private val httpClient: HttpClient) {

    suspend fun retrieveUserProfile(
        bearerToken: String
    ): Result<SpotifyProfileDto, SpotifyErrorDto> {
        return httpClient
            .get(PROFILE_URL) {
                accept(ContentType.Application.Json)
                bearerAuth(bearerToken)
            }
            .toResult()
    }
}
