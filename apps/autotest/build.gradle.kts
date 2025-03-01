plugins {
    application
    kotlin("jvm")
    id("org.jlleitschuh.gradle.ktlint")
}

tasks {

    test {
        useJUnitPlatform()
    }
}

dependencies {
    val ktorVersion = "1.5.3"
    val junitVersion = "5.7.1"

    api(project(":libs:hops-common-fhir"))
    implementation("io.ktor:ktor-auth:$ktorVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("no.nav.security:token-validation-ktor:1.3.5")
    implementation("ca.uhn.hapi.fhir:org.hl7.fhir.r4:5.3.11")
    implementation("ca.uhn.hapi.fhir:hapi-fhir-base:5.3.3")
    implementation("ca.uhn.hapi.fhir:hapi-fhir-structures-r4:5.3.3")

    runtimeOnly("ch.qos.logback:logback-classic:1.2.3")
    runtimeOnly("io.ktor:ktor-server-netty:$ktorVersion")
    runtimeOnly("net.logstash.logback:logstash-logback-encoder:6.6")
    testImplementation(kotlin("test-junit5"))
    testImplementation("io.ktor:ktor-server-test-host:$ktorVersion") { exclude(group = "junit", module = "junit") }
    testImplementation("no.nav.security:mock-oauth2-server:0.3.2") { exclude(group = "junit", module = "junit") }
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testRuntimeOnly("junit:junit:4.13.2") { because("Required by mock-oauth2-server.") }
}
