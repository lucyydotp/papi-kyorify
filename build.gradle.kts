import dev.triumphteam.helper.papi
import dev.triumphteam.helper.spigot

plugins {
    java
    id("me.mattstudios.triumph") version "0.2.8"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "net.lucypoulton"
version = "1.0"

repositories {
    mavenCentral()
    papi()
    spigot()
}

tasks.shadowJar {
    relocate("net.kyori", "net.lucypoulton.kyorify.shaded.kyori")
    minimize()
    archiveClassifier.set("")
}

tasks.build { dependsOn(tasks.shadowJar) }

dependencies {
    compileOnly(papi("2.11.1"))
    compileOnly("org.jetbrains:annotations:23.0.0")
    compileOnly(spigot("1.18.2"))
    listOf("minimessage", "serializer-legacy").map { implementation("net.kyori:adventure-text-$it:4.10.1") }
}