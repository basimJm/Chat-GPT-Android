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

rootProject.name = "ChatGpt"
include(":app")
include(":presentation")
include(":data")
include(":domin")
include(":core")
include(":presentation:splash")
include(":presentation:register")
include(":common")
include(":common:sharedui")
include(":presentation:chat")
