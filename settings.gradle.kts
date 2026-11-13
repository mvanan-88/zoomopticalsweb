pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "zoomopticals"
include(":composeApp")
// Future modules: :backend and :admin can be added when those parts are ready.
