package no.nav.helse.hops.utils

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class DockerComposeEnvTest {

    @Test
    fun changeUrl() {
        val newurl = DockerComposeEnv().changeUrl("http://kafka:8900")
        assertEquals("http://localhost:8900", newurl)
    }
}