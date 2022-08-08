package com.chesire.benzaiten.plugins.koin

import com.chesire.benzaiten.plugins.koin.modules.defaultModules
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin

/**
 * Installs and configures the [Koin] plugin.
 */
fun Application.configureKoin() {
    install(Koin) {
        modules(defaultModules)
    }
}
