package com.chesire.benzaiten.routing.auth.service

import com.chesire.benzaiten.Const
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get

class AuthService {

    suspend fun performRequest() {
        val client = HttpClient(CIO)
        // var state = generateRandomString(16) // TODO: generate random string
        val response = client.get("https://accounts.spotify.com/authorize") {
            url.parameters.append("response_type", "token")
            url.parameters.append("client_id", Const.CLIENT_ID)
            url.parameters.append("scope", Const.SCOPE)
            url.parameters.append("redirect_uri", Const.REDIRECT_URL)
            // url.parameters.append("state", state)
        }
        print("Response - $response")
    }
}
