package com.chesire.benzaiten

import com.chesire.benzaiten.routing.configureRouting
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun `verify starts`() {
        withTestApplication({ configureRouting() }) {
            handleRequest(HttpMethod.Get, "/status/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals("Benzaiten status OK!", response.content)
            }
        }
    }
}
