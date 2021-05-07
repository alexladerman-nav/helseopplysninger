package no.nav.helse.hops.utils

import io.github.cdimascio.dotenv.Dotenv
import io.github.cdimascio.dotenv.dotenv
import io.ktor.http.*


data class DockerComposeEnv(val dotenv: Dotenv = dotenv { filename = "autotest.env" }) {

    val kafkaBrokers: String = envVar("KAFKA_BROKERS").replace("kafka", "localhost")
    val oauth2wellKnownUrl: String = envVarUrl("AZURE_APP_WELL_KNOWN_URL")

    private fun envVar(key: String): String {
        return dotenv[key] ?: throw RuntimeException("Missing required variable \"$key\"")
    }

    private fun envVarUrl(key: String): String {
        return changeUrl(envVar(key));
    }

    fun changeUrl(url: String): String {
        val input = Url(url).copy(host = "localhost")
        return input.toString();
    }
}
