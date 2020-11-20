import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    idea
    kotlin("jvm") version "1.4.10"
    id("org.jetbrains.kotlin.kapt") version "1.4.10"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.10"
    id("com.github.johnrengelman.shadow") version "6.0.0"
    id("org.jlleitschuh.gradle.ktlint") version "9.4.0"
    id("org.jlleitschuh.gradle.ktlint-idea") version "9.4.0"
    id("io.micronaut.application") version "1.0.3"
}

val micronautVersion: String by project
val kotlinVersion: String by project

version = "0.1"
group = "ai.whylabs.repro"

repositories {
    mavenCentral()
    jcenter()
}

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        module("ai.whylabs.repro")
        annotations("ai.whylabs.*")
    }
}

dependencies {
    // basic micronaut
    implementation(platform("io.micronaut:micronaut-bom:$micronautVersion"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("io.micronaut:micronaut-runtime:$micronautVersion")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime:$micronautVersion")
    implementation("io.micronaut:micronaut-http-server-netty:$micronautVersion")
    implementation("io.micronaut:micronaut-http-client:$micronautVersion")
    implementation("io.swagger.core.v3:swagger-annotations:$micronautVersion")
    implementation("io.micronaut.security:micronaut-security:$micronautVersion")

    // annotations
    implementation("javax.annotation:javax.annotation-api")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    kapt(platform("io.micronaut:micronaut-bom:$micronautVersion"))
    kapt("io.micronaut:micronaut-inject-java")
    kapt("io.micronaut:micronaut-validation")
    kapt("io.micronaut.security:micronaut-security-annotations")
    kapt("io.micronaut.configuration:micronaut-openapi")
    annotationProcessor("io.micronaut.configuration:micronaut-openapi:$micronautVersion")

    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.13.3")

    // testing
    kaptTest(platform("io.micronaut:micronaut-bom:$micronautVersion"))
    kaptTest("io.micronaut:micronaut-inject-java")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("io.micronaut.test:micronaut-test-junit5")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

application {
    mainClass.set("ai.whylabs.repro.Application")
}

java {
    sourceCompatibility = JavaVersion.toVersion("1.8")
}

tasks.test {
    useJUnitPlatform()
}

allOpen {
    annotation("io.micronaut.aop.Around")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.javaParameters = true
}

tasks.named<ShadowJar>("shadowJar") {
    mergeServiceFiles()
}
