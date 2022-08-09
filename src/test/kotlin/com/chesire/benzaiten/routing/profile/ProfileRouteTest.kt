package com.chesire.benzaiten.routing.profile

import com.chesire.benzaiten.routing.configureRouting
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals

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
}
