import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.4.10"
    id("org.jetbrains.kotlin.kapt") version "1.4.10"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.4.10"
    id("com.github.johnrengelman.shadow") version "6.0.0"
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
    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")

    // basic micronaut
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut:micronaut-validation")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut:micronaut-http-server-netty")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut.security:micronaut-security")
    implementation("io.micronaut.kotlin:micronaut-kotlin-extension-functions")

    implementation("io.swagger.core.v3:swagger-annotations:2.1.5")

    // annotations
    implementation("javax.annotation:javax.annotation-api")

    kapt("io.micronaut.openapi:micronaut-openapi")

    runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl:2.14.0")
    runtimeOnly("org.apache.logging.log4j:log4j-layout-template-json:2.14.0")

}

application {
    mainClass.set("ai.whylabs.repro.ApplicationKt")
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
