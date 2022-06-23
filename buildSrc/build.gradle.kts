plugins {
    `kotlin-dsl`
}

repositories {
    maven {
        name = "arhs-nexus-read-only"
        url = uri("https://nexus3.arhs-developments.com/repository/BPC.MAS-maven-Read/")
        credentials {
            username = cleverGetProperty(project, "bpc.arhs.nexus.username")
            password = cleverGetProperty(project, "bpc.arhs.nexus.password")
        }
    }
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.6.21"))
    implementation("org.openapitools:openapi-generator-gradle-plugin:5.3.1")
    implementation("com.bmuschko:gradle-docker-plugin:7.2.0")
    implementation("com.google.googlejavaformat:google-java-format:1.15.0")
    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.7.0")
    implementation("com.gorylenko.gradle-git-properties:gradle-git-properties:2.4.0-rc2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

kotlin {
    target {
        version = "11"
    }
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(11))
}

tasks.withType<Test> {
    useJUnitPlatform()
}

fun cleverGetProperty(project: Project, key: String): String {
    val value = if (project.hasProperty(key)) {
        project.property(key) as String
    } else {
        val envPropKey = key.toUpperCase().replace(".", "_")
        System.getenv()[envPropKey] as String
    }
    project.logger.debug("Resolved $key to $value")
    return value;
}