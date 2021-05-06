package no.nav.helse.hops

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Date

fun Date.toLocalDateTime(): LocalDateTime = LocalDateTime.ofInstant(toInstant(), ZoneId.systemDefault())

/** YYYY-MM-DDThh:mm:ss.sssZ **/
fun Date.toIsoString(): String = toLocalDateTime().toIsoString()

/** YYYY-MM-DDThh:mm:ss.sssZ **/
fun LocalDateTime.toIsoString(): String =
    atZone(ZoneId.systemDefault()).truncatedTo(ChronoUnit.MILLIS).format(DateTimeFormatter.ISO_INSTANT)
