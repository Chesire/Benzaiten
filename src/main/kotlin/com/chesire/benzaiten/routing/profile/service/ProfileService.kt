package com.chesire.benzaiten.routing.profile.service

import com.chesire.benzaiten.ext.cast
import com.chesire.benzaiten.ext.isSuccessful
import com.chesire.benzaiten.routing.SpotifyErrorDomain
import com.chesire.benzaiten.routing.profile.data.SpotifyProfileDto
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.accept
import io.ktor.client.request.bearerAuth
import io.ktor.client.request.get
import io.ktor.http.ContentType

private const val PROFILE_URL = "https://api.spotify.com/v1/me"

class ProfileService(private val httpClient: HttpClient) {

    suspend fun retrieveUserProfile(bearerToken: String): Result<SpotifyProfileDto, SpotifyErrorDomain> {
        val response = httpClient.get(PROFILE_URL) {
            accept(ContentType.Application.Json)
            bearerAuth(bearerToken)
        }

        return if (response.isSuccessful) {
            Ok(response.cast())
        } else {
            Err(response.cast())
        }
    }
}
