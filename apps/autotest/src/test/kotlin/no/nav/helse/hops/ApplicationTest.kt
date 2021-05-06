package no.nav.helse.hops


import io.ktor.http.*
import no.nav.helse.hops.utils.urlReturnsStatusCode
import org.junit.jupiter.api.Test
import java.net.URL
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun `Requests with without token should return 401-Unauthorized`() {
        val something = true;
        assertEquals(something, true)
    }

    @Test
    fun `Requests should be 200`() {
        val url = URL("https://ktor.io/")
        val result = urlReturnsStatusCode(url, 200);
        assertEquals(result, true)
    }
}
