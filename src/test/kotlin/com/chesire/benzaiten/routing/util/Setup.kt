package com.chesire.benzaiten.routing.util

import com.chesire.benzaiten.plugins.configureSerialization
import com.chesire.benzaiten.plugins.koin.modules.defaultModules
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.config.MapApplicationConfig
import io.ktor.server.testing.ApplicationTestBuilder
import org.koin.core.context.startKoin
import org.koin.core.module.Module

/**
 * Installs the plugins required to run the tests.
 * Passing [modules] will allow overriding and providing modules provided via koin.
 */
fun ApplicationTestBuilder.installTestPlugins(modules: () -> List<Module> = { emptyList() }) {
    application {
        startKoin {
            modules(defaultModules)
            modules(modules())
        }
        configureSerialization()
    }
}

/**
 * Sets up the environment ready to run the tests.
 */
fun ApplicationTestBuilder.primeTestEnvironment() {
    environment {
        config = MapApplicationConfig()
    }
}

/**
 * Creates the [HttpClient] needed to run the tests.
 */
fun ApplicationTestBuilder.createTestClient(): HttpClient {
    return createClient {
        install(ContentNegotiation) {
            json()
        }
    }
}
