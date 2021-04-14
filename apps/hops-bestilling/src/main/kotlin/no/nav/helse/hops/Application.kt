package no.nav.helse.hops

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import no.nav.helse.hops.infrastructure.BestillingProducerJob
import no.nav.helse.hops.infrastructure.HttpRequestKoinScope
import no.nav.helse.hops.infrastructure.KoinBootstrapper
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.getKoin

@Suppress("unused") // Referenced in application.conf
fun Application.module() {
    install(DefaultHeaders)
    install(CallLogging)
    install(Koin) {
        modules(KoinBootstrapper.module)
    }

    routing {
        val koin = getKoin()

        get("/isReady") {
            call.respondText("ready")
        }
        get("/isAlive") {
            call.respondText("alive")
        }
        get("/test") {
            // TODO: Move to a custom ktor-feature.
            val scope = koin.createScope<HttpRequestKoinScope>()
            try {
                val service = scope.get<BestillingProducerJob>()
            } finally {
                scope.close()
            }
            call.respondText("message published")
        }
    }
}