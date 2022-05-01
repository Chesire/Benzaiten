package com.chesire.benzaiten.routing.auth.service

import com.chesire.benzaiten.Const
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get

private const val AUTHORIZE = "https://accounts.spotify.com/authorize"

class AuthService {
    private val client = HttpClient(CIO)

    suspend fun performRequest() {
        // var state = generateRandomString(16) // TODO: generate random string
        val response = client.get(AUTHORIZE) {
            url.parameters.append("response_type", "token")
            url.parameters.append("client_id", Const.CLIENT_ID)
            url.parameters.append("scope", Const.SCOPE)
            url.parameters.append("redirect_uri", Const.REDIRECT_URL)
            // url.parameters.append("state", state)
        }
        print("Response - $response")
    }
}
