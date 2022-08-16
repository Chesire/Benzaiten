package com.chesire.benzaiten.service.auth

import com.chesire.benzaiten.Const
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get

private const val AUTHORIZE = "https://accounts.spotify.com/authorize"

class AuthService {
    private val client = HttpClient(CIO)

    suspend fun performRequest() {
        /*
        TODO:
        This is not currently implemented correctly, for now hit the URL in your browser to generate a token, then check the URL for it.
        Make sure that the server is running.
        https://accounts.spotify.com/authorize/?response_type=token&client_id=6b3de92e9e0241e0b07b7d9d938448d4&scope=user-read-private%20user-read-email&redirect_uri=http://localhost:8080/authcallback/
         */
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
