package com.chesire.benzaiten.routing.profile.service

import com.chesire.benzaiten.routing.SpotifyErrorDto
import com.chesire.benzaiten.routing.profile.data.ExplicitContent
import com.chesire.benzaiten.routing.profile.data.ExternalUrls
import com.chesire.benzaiten.routing.profile.data.Followers
import com.chesire.benzaiten.routing.profile.data.Image
import com.chesire.benzaiten.routing.profile.data.SpotifyProfileDto
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ProfileServiceTest {

    @Test
    fun `successful call to profile, returns Ok with data`() = runBlocking {
        val expected = SpotifyProfileDto(
            country = "country",
            displayName = "displayName",
            email = "email",
            explicitContent = ExplicitContent(
                filterEnabled = false,
                filterLocked = false
            ),
            externalUrls = ExternalUrls(
                spotify = "spotify"
            ),
            followers = Followers(
                href = "href",
                total = 0
            ),
            href = "href",
            id = "id",
            images = listOf(
                Image(
                    height = null,
                    url = "url",
                    width = null
                )
            ),
            product = "product",
            type = "type",
            uri = "uri"
        )
        val mockEngine = MockEngine { _ ->
            respond(
                content = Json.encodeToString(expected),
                status = HttpStatusCode.OK,
                headers = headersOf()
            )
        }
        val client = HttpClient(mockEngine)
        val profileService = ProfileService(client)

        val result = profileService.retrieveUserProfile("token")

        check(result is Ok)
        assertEquals(expected, result.value)
    }

    @Test
    fun `failure call to profile, returns Err with error domain`() = runBlocking {
        val expected = SpotifyErrorDto(
            status = 100,
            message = "message"
        )
        val mockEngine = MockEngine { _ ->
            respond(
                content = Json.encodeToString(expected),
                status = HttpStatusCode.BadRequest,
                headers = headersOf()
            )
        }
        val client = HttpClient(mockEngine)
        val profileService = ProfileService(client)

        val result = profileService.retrieveUserProfile("token")

        check(result is Err)
        assertEquals(expected, result.error)
    }
}
