import dev.triumphteam.helper.papi
import dev.triumphteam.helper.spigot

plugins {
    java
    id("me.mattstudios.triumph") version "0.2.8"
}

group = "net.lucypoulton"
version = "1.5.3"

repositories {
    mavenCentral()
    papi()
    spigot()
}

tasks.test { useJUnitPlatform() }



dependencies {
    compileOnly(papi("2.11.2"))
    compileOnly("org.jetbrains:annotations:23.0.0")
    compileOnly(spigot("1.19"))

    testImplementation(platform("org.junit:junit-bom:5.8.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}
