import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    application
}

application {
    mainClass.set("MainKt")
}

group = "io.lucin"
version = "1.0"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    val version = "master-SNAPSHOT"

    implementation("com.github.Anuken.Arc:discord:$version")
    implementation("com.github.Anuken.Arc:arc-core:$version")
    implementation("com.github.Anuken.Arc:backend-headless:$version")

    implementation("com.google.code.gson:gson:2.9.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = application.mainClass
    }

    archiveFileName.set("rpc.jar")

    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}