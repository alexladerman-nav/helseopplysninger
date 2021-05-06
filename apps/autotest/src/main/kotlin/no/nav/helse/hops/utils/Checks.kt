package no.nav.helse.hops.utils

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory


val client = HttpClient()

fun urlReturnsStatusCode(url: Url, code: Int): Boolean {
    return try {
        val response: io.ktor.client.statement.HttpResponse = runBlocking {
            client.request(url)
        }
        response.status.value == code
    } catch(e: Exception) {
        LoggerFactory.getLogger("Autotest").warn("urlReturnsStatusCode failed: " + e.message);
        false;
    }


}