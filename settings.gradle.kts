pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Happy Hours"
include(":app")
include(":features")
include(":features:auth")
include(":features:auth:presentation")
include(":features:auth:domain")
include(":data")
include(":core-ui")
