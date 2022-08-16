package com.chesire.benzaiten.routing.profile

import com.chesire.benzaiten.routing.ErrorDomain
import com.chesire.benzaiten.routing.util.createTestClient
import com.chesire.benzaiten.routing.util.installTestPlugins
import com.chesire.benzaiten.routing.util.primeTestEnvironment
import com.chesire.benzaiten.service.SpotifyErrorDto
import com.chesire.benzaiten.service.SpotifyErrorDtoDetails
import com.chesire.benzaiten.service.profile.ExplicitContent
import com.chesire.benzaiten.service.profile.ExternalUrls
import com.chesire.benzaiten.service.profile.Followers
import com.chesire.benzaiten.service.profile.Image
import com.chesire.benzaiten.service.profile.SpotifyProfileDto
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest

class ProfileRouteTest : AutoCloseKoinTest() {

    @Test
    fun `Verify missing token responds with error`() = testApplication {
        val client = createTestClient()
        primeTestEnvironment()
        installTestPlugins()
        routing {
            profile()
        }

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
    fun `Verify sending successful request to spotify profile api`() = testApplication {
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
        val client = createTestClient()
        primeTestEnvironment()
        installTestPlugins() {
            listOf(
                module {
                    single { client }
                }
            )
        }
        routing {
            profile()
        }
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

    @Test
    fun `Verify sending failure request to spotify profile api`() = testApplication {
        val client = createTestClient()
        primeTestEnvironment()
        installTestPlugins() {
            listOf(
                module {
                    single { client }
                }
            )
        }
        routing {
            profile()
        }
        externalServices {
            hosts("https://api.spotify.com") {
                routing {
                    get("/v1/me") {
                        call.respondText(
                            Json.encodeToString(
                                SpotifyErrorDto(
                                    SpotifyErrorDtoDetails(
                                        status = HttpStatusCode.BadRequest.value,
                                        message = "Received failure from test api"
                                    )
                                )
                            ),
                            ContentType.Application.Json,
                            HttpStatusCode.BadRequest
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

        val expected = ErrorDomain(HttpStatusCode.BadRequest.value.toString(), "Received failure from test api")
        assertEquals(HttpStatusCode.BadRequest, response.status)
        assertEquals(Json.encodeToString(expected), response.bodyAsText())
    }
}
