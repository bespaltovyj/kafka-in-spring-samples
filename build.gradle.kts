plugins {
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.4"

    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"
}

group = "io.bespaltovyj.samples"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.kafka:spring-kafka")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")


    // указаны для демонстрации работы
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap:4.1.1")
    implementation("com.playtika.testcontainers:embedded-kafka:3.1.4")
}

kotlin {
    jvmToolchain(17)
}