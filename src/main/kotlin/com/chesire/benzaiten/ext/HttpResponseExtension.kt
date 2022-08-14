package com.chesire.benzaiten.ext

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

/**
 * Checks if this [HttpResponse] was a success status code.
 */
val HttpResponse.isSuccessful: Boolean
    get() = status == HttpStatusCode.OK ||
        status == HttpStatusCode.Created ||
        status == HttpStatusCode.Accepted

/**
 * Attempts to cast the content within this [HttpResponse] into an instance of [T].
 */
suspend inline fun <reified T> HttpResponse.cast(): T {
    return Json.decodeFromString(bodyAsText())
}

/**
 * Casts this [HttpResponse] into a [Result] object based on if the call is successful.
 */
suspend inline fun <reified V, reified E> HttpResponse.toResult(): Result<V, E> {
    return if (isSuccessful) {
        Ok(cast())
    } else {
        Err(cast())
    }
}
