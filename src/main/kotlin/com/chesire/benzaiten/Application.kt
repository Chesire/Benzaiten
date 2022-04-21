package com.chesire.benzaiten

import com.chesire.benzaiten.routing.configureRouting
import com.chesire.benzaiten.plugins.configureSerialization
import com.chesire.benzaiten.plugins.configureStatusPages
import com.chesire.benzaiten.plugins.koin.configureKoin
import io.ktor.server.application.Application

/**
 * Kotlin main method - starts the Ktor engine.
 * After running in IDEA this should be available at localhost:8080.
 */
fun main(args: Array<String>) = io.ktor.server.netty.EngineMain.main(args)

/**
 * Ktor [module], will configure the ktor client.
 */
@Suppress("unused")
fun Application.module() {
    configureKoin()
    configureSerialization()
    configureStatusPages()
    configureRouting()
}
