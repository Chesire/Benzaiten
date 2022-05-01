package com.chesire.benzaiten.plugins.koin.modules

import com.chesire.benzaiten.routing.profile.service.ProfileService
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import org.koin.core.module.Module
import org.koin.dsl.module

val httpClientModule = module {
    single {
        HttpClient(CIO) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.HEADERS
            }
        }
    }
}

val profileServiceModule = module {
    single {
        ProfileService(get())
    }
}

val defaultModules = listOf<Module>(
    httpClientModule,
    profileServiceModule
)
