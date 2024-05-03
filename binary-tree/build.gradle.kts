plugins {
    kotlin("jvm") version "1.9.23"
}

group = "com.spyderrsh.cricut"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(13)
}