package com.chesire.benzaiten.plugins.koin.modules

import com.chesire.benzaiten.service.profile.ProfileService
import org.koin.dsl.module

val profileServiceModule = module {
    single {
        ProfileService(get())
    }
}
