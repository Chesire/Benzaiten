package com.chesire.benzaiten.plugins.koin.modules

import com.chesire.benzaiten.routing.profile.service.ProfileService
import org.koin.dsl.module

val profileServiceModule = module {
    single {
        ProfileService(get())
    }
}
