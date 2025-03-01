package no.nav.helse.hops.domain

import ca.uhn.fhir.rest.client.api.IGenericClient
import org.hl7.fhir.r4.model.Bundle
import org.hl7.fhir.r4.model.Practitioner
import org.hl7.fhir.r4.model.Task

interface FkrFacade {
    suspend fun practitionerName(hprNr: Int): String
}

class FkrFacadeImpl(private val _fhirClient: IGenericClient) : FkrFacade {
    override suspend fun practitionerName(hprNr: Int): String {

        val bundle = _fhirClient
            .search<Bundle>()
            .byUrl("Practitioner?identifier=urn:oid:2.16.578.1.12.4.1.4.4|$hprNr")
            .execute()

        val practitioner = bundle.entry.firstOrNull()?.resource as Practitioner?
        val name = practitioner?.name?.firstOrNull()?.family ?: ""

        val tasks = bundle.entry.mapNotNull { it.resource as? Task }

        return name
    }
}
