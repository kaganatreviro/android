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
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

rootProject.name = "Happy Hours"
include(":app")
include(":features")
include(":features:auth")
include(":features:auth:presentation")
include(":features:auth:domain")
include(":features:main")
include(":data")
include(":core-ui")
include(":core")
include(":features:main:presentation")
include(":features:main:domain")
