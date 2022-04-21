package com.example.plugins.koin

import io.ktor.events.EventDefinition
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationPlugin
import io.ktor.server.application.ApplicationStopping
import io.ktor.util.AttributeKey
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.java.KoinJavaComponent.getKoin

/*
This file is currently copied from https://github.com/InsertKoinIO/koin/pull/1266/files, so we can get the changes that
should soon be in the Koin main branch.
Once Koin supports the new Ktor version, this file should be deleted.
 */

val KoinApplicationStarted = EventDefinition<KoinApplication>()
val KoinApplicationStopPreparing = EventDefinition<KoinApplication>()
val KoinApplicationStopped = EventDefinition<KoinApplication>()

inline fun <reified T : Any> inject(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null,
) = lazy { get<T>(qualifier, parameters) }

inline fun <reified T : Any> get(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null,
) = getKoin().get<T>(qualifier, parameters)

object KoinPlugin : ApplicationPlugin<Application, KoinApplication, Unit> {

    override val key: AttributeKey<Unit>
        get() = AttributeKey("Koin")

    override fun install(
        pipeline: Application,
        configure: KoinApplication.() -> Unit,
    ) {
        val monitor = pipeline.environment.monitor
        val koinApplication = startKoin(appDeclaration = configure)
        monitor.raise(KoinApplicationStarted, koinApplication)

        monitor.subscribe(ApplicationStopping) {
            monitor.raise(KoinApplicationStopPreparing, koinApplication)
            stopKoin()
            monitor.raise(KoinApplicationStopped, koinApplication)
        }
    }
}
