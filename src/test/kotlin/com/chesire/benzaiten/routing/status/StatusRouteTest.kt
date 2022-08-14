package com.chesire.benzaiten.routing.status

import com.chesire.benzaiten.routing.util.createTestClient
import com.chesire.benzaiten.routing.util.installTestPlugins
import com.chesire.benzaiten.routing.util.primeTestEnvironment
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals
import org.koin.test.AutoCloseKoinTest

class StatusRouteTest : AutoCloseKoinTest() {

    @Test
    fun `Verify status endpoint returns OK`() = testApplication {
        val client = createTestClient()
        primeTestEnvironment()
        installTestPlugins()
        routing {
            status()
        }

        val response = client.get("/status/")

        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Benzaiten status OK!", response.bodyAsText())
    }
}
