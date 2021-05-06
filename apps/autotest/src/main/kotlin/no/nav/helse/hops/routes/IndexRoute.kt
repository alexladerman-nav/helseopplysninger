package no.nav.helse.hops.routes

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import no.nav.helse.hops.auth.Auth
import no.nav.helse.hops.utils.Fixtures
import no.nav.helse.hops.utils.checkSutServices
import no.nav.helse.hops.utils.getSutServices

fun Routing.indexRoutes() {
    val auth = Auth()
    get("/") {
        call.respondText("Hello Autotest")
    }
    get("/changed") {
        call.respondText("api")
    }
    get("/token") {
        val token = auth.token("myscope");
        call.respondText(token.toString())
    }
    get("/bestilling") {
        val fileContent = Fixtures().bestillingsBundle();
        call.respondText(fileContent.toString())
    }
    get("/apps") {
        val apps = checkSutServices();
        call.respondText(Json.encodeToString(apps))
    }
}
