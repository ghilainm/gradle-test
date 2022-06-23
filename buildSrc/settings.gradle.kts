pluginManagement {
    repositories {
        fun cleverGetProperty(key: String): String {
            val value = if (settings.extra.has(key)) {
                settings.extra.get(key) as String
            } else {
                val envPropKey = key.toUpperCase().replace(".", "_")
                java.util.Objects.requireNonNull(
                    System.getenv()[envPropKey] as String,
                    "The value of the property $key must either be a gradle project property or an environment property $envPropKey"
                )
            }
            return value;
        }
        maven {
            name = "arhs-nexus-read-only"
            url = uri("https://nexus3.arhs-developments.com/repository/BPC.MAS-maven-Read/")
            credentials {
                username = cleverGetProperty("bpc.arhs.nexus.username")
                password = cleverGetProperty("bpc.arhs.nexus.password")
            }
        }
    }
}
rootProject.name = "mas-gradle-conventions"