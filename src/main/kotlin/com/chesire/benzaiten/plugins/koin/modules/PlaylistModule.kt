package com.chesire.benzaiten.plugins.koin.modules

import com.chesire.benzaiten.service.playlist.PlaylistService
import org.koin.dsl.module

val playlistServiceModule = module {
    single {
        PlaylistService(get())
    }
}
