package no.nav.helse.hops.routes

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import no.nav.helse.hops.auth.Auth
import no.nav.helse.hops.utils.Fixtures

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
}
