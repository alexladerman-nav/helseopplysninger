package no.nav.helse.hops.fhir

import ca.uhn.fhir.context.FhirContext
import ca.uhn.fhir.context.FhirVersionEnum
import org.hl7.fhir.instance.model.api.IBaseResource
import org.hl7.fhir.r4.model.*

fun Bundle.addResource(resource: Resource) {
    val entry = Bundle.BundleEntryComponent()
    entry.fullUrl = "urn:uuid:${resource.id}"
    entry.resource = resource
    addEntry(entry)
}

fun IBaseResource.toJson(): String {
    val ctx = FhirContext.forCached(FhirVersionEnum.R4)
    val parser = ctx.newJsonParser()
    return parser.encodeResourceToString(this)
}

fun createFhirMessage(): Bundle {
    val eventType = Coding("nav:hops:eventType", "bestilling", "Bestilling")
    val messageHeader = MessageHeader(eventType, MessageHeader.MessageSourceComponent(UrlType("")))
    messageHeader.destination = listOf(MessageHeader.MessageDestinationComponent(UrlType("http://localhost:1234")))
    val task = Task()
    val questionnaire = Questionnaire()

    return Bundle().apply {
        type = Bundle.BundleType.MESSAGE
        addResource(messageHeader)
        addResource(task)
        addResource(questionnaire)
    }
}