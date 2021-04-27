import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("io.codearte.nexus-staging")  version "0.30.0"
    kotlin("jvm") version "1.4.32"
}

group = "com.craftandtechnology"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
    jcenter()
}

val logbackVersion = "1.2.3"
val assertjVersion = "3.19.0"
val junitJupiterVersion = "5.7.1"
val junitPlatformVersion = "1.7.1"
val lombokVersion = "1.18.20"

dependencies {
    implementation("ch.qos.logback:logback-core:$logbackVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")

    testCompileOnly("org.projectlombok:lombok:$lombokVersion")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
    testImplementation("org.junit.platform:junit-platform-runner:$junitPlatformVersion")
    testImplementation("org.assertj:assertj-core:$assertjVersion")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
