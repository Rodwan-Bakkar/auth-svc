import org.springframework.boot.gradle.tasks.run.BootRun

fun loadEnvironment(): Map<String, String> {
    // For dev env
    val primary = File(".env")
    // For prod env (Render)
    val envFile = if (primary.exists()) primary else File("/etc/secrets/.env")

    return if (envFile.exists()) {
        // Load from .env file for local development.
        envFile
            .readLines()
            .mapNotNull { line ->
                val trimmed = line.trim()
                // Ignore empty lines and comments
                if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                    null
                } else {
                    // Split the key and value
                    val (key, value) = trimmed.split("=", limit = 2)
                    key to value
                }
            }.toMap()
    } else {
        // If the .env file doesn't exist, use system environment variables.
        System.getenv()
    }
}

val env = loadEnvironment()

println("Loaded variables: $env")
println("Loaded DB_URL: ${env["DB_URL"]}")
println("Loaded DB_USER: ${env["DB_USER"]}")

apply(plugin = "org.flywaydb.flyway")

plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.5.5"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.flywaydb.flyway") version "11.12.0"
    id("nu.studer.jooq") version "10.1.1"
    id("org.jlleitschuh.gradle.ktlint") version "13.1.0"
}

group = "com"
version = "0.0.1-SNAPSHOT"
description = "Auth Service"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

// Explicitly add the necessary dependencies to the buildscript classpath.
// This ensures they are available to the Flyway plugin during its configuration phase.
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.flywaydb:flyway-gradle-plugin:11.12.0")
        classpath("org.flywaydb:flyway-database-postgresql:11.12.0")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jooq")

    implementation("org.springframework.boot:spring-boot-starter-security:4.0.0-M2")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.flywaydb:flyway-core:11.12.0")
    implementation("org.flywaydb:flyway-database-postgresql:11.12.0")

    runtimeOnly("org.postgresql:postgresql:42.7.7")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("org.jooq:jooq:3.20.7")

    jooqGenerator("org.postgresql:postgresql:42.7.7")
    jooqGenerator("org.jooq:jooq:3.20.7")
    jooqGenerator("org.jooq:jooq-meta:3.20.7")
    jooqGenerator("org.jooq:jooq-codegen:3.20.7")

    compileOnly("jakarta.servlet:jakarta.servlet-api:6.1.0")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("io.jsonwebtoken:jjwt-api:0.13.0")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.13.0")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.13.0")

    implementation("io.github.cdimascio:java-dotenv:5.2.2")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.13")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

ktlint {
    ignoreFailures.set(false)
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
    }
}

flyway {
    url = env["DB_URL"]
    user = env["DB_USER"]
    password = env["DB_PASSWORD"]
    baselineOnMigrate = true
    locations = arrayOf("filesystem:src/main/resources/db/migration")
}

jooq {
    version.set("3.20.7")
    configurations {
        create("main") {
            jooqConfiguration.apply {
                logging = org.jooq.meta.jaxb.Logging.WARN
                jdbc.apply {
                    driver = "org.postgresql.Driver"
                    url = env["DB_URL"]
                    user = env["DB_USER"]
                    password = env["DB_PASSWORD"]
                }
                generator.apply {
                    name = "org.jooq.codegen.KotlinGenerator"
                    database.apply {
                        inputSchema = "public"
                    }
                    target.apply {
                        packageName = "com.auth.jooq.generated"
                        directory =
                            layout.buildDirectory
                                .dir("generated-src/jooq")
                                .get()
                                .asFile.path
                    }
                }
            }
        }
    }
}

tasks.named("generateJooq") {
    dependsOn("flywayMigrate")
}
sourceSets["main"].kotlin.srcDir(
    layout.buildDirectory
        .dir("generated-src/jooq")
        .get()
        .asFile.path,
)

// // Configure the bootRun task to include .env properties as environment variables
tasks.withType<BootRun> {
    environment(env)
}

// Configure the test task to include .env properties as environment variables
tasks.withType<Test> {
    environment(env)
    useJUnitPlatform()
}