package com.chesire.benzaiten.routing.profile

import com.chesire.benzaiten.routing.configureRouting
import com.chesire.benzaiten.routing.profile.data.ExplicitContent
import com.chesire.benzaiten.routing.profile.data.ExternalUrls
import com.chesire.benzaiten.routing.profile.data.Followers
import com.chesire.benzaiten.routing.profile.data.Image
import com.chesire.benzaiten.routing.profile.data.SpotifyProfileDto
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ProfileRouteTest {

    @Test
    fun `Verify missing token responds with error`() = testApplication {
        application { configureRouting() }

        val response = client.get("/profile/")

        assertEquals(HttpStatusCode.Unauthorized, response.status)
        assertEquals(
            """
                {"error":"No spotify token","error_description":"Please provide the spotify-token header."}
            """.trimIndent(),
            response.bodyAsText()
        )
    }

    @Test
    fun `Verify sending request to spotify api`() = testApplication {
        val expected = Json.encodeToString(
            SpotifyProfileDto(
                country = "country",
                displayName = "displayName",
                email = "email",
                explicitContent = ExplicitContent(true, false),
                externalUrls = ExternalUrls("spotify"),
                followers = Followers(null, 0),
                href = "href",
                id = "id",
                images = listOf(Image(null, "", null)),
                product = "product",
                type = "type",
                uri = "uri"
            )
        )
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }
        application { configureRouting() }
        externalServices {
            hosts("https://api.spotify.com") {
                routing {
                    get("/v1/me") {
                        call.respondText(
                            expected,
                            ContentType.Application.Json,
                            HttpStatusCode.OK
                        )
                    }
                }
            }
        }

        val response = client.get("/profile/") {
            accept(ContentType.Application.Json)
            headers {
                append("spotify-token", "x")
            }
        }

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(expected, response.bodyAsText())
    }
}
