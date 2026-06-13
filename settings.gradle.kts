pluginManagement {
    repositories {
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "KMP Showcase"
include(":contracts", ":shared", ":androidApp", ":iosApp", ":server")

// Local-only learning module, gitignored — present only on machines that have it
if (file("test-server").exists()) {
    include(":test-server")
}
