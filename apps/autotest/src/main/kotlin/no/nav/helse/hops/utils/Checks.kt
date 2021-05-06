package no.nav.helse.hops.utils

import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.runBlocking
import java.net.URL


val client = HttpClient()

fun urlReturnsStatusCode(url: URL, code: Int): Boolean {
    val response: io.ktor.client.statement.HttpResponse = runBlocking {
        client.request(url)
    }
    return response.status.value == code
}