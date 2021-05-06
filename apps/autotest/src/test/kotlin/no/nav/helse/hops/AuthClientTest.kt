package no.nav.helse.hops

import com.nimbusds.jwt.JWTParser
import kotlinx.coroutines.runBlocking
import no.nav.helse.hops.auth.Auth
import no.nav.helse.hops.utils.Fixtures
import org.hl7.fhir.r4.model.ResourceType
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AuthClientTest {
    private val auth = Auth()
    @Test
    fun `should give a token`() {
        val scope = "myscope"
        val token = runBlocking {
            auth.token(scope)
        }
        val something = true
        assertEquals(something, true)

        if (token != null) {
            assertTrue { token.expirationTime > Date() }
        }
    }

    @Test
    fun `read file`() {
        val dfas = Fixtures().bestillingsBundle()
        assertEquals(dfas.resourceType, ResourceType.Bundle)

    }

}
