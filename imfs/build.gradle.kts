plugins {
    kotlin("jvm")
}

group = "com.spyderrsh.cricut"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("script-runtime"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(13)
}