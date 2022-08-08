package com.chesire.benzaiten

import com.chesire.benzaiten.routing.configureRouting
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun `verify starts`() = testApplication {
        application { configureRouting() }

        val response = client.get("/status/")

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Benzaiten status OK!", response.bodyAsText())
    }
}
