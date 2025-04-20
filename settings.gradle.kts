pluginManagement {
    listOf(repositories, dependencyResolutionManagement.repositories).forEach {
        it.apply {
            google {
                content {
                    includeGroupByRegex(".*google.*")
                    includeGroupByRegex(".*android.*")
                }
            }
            mavenCentral()
        }
    }
}

rootProject.name = "KMPSample"
include(":androidApp")
include(":shared")
includeBuild("build-logic")

