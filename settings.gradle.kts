@file: Suppress("UnstableApiUsage")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.aliyun.com/repository/public")
//        maven("https://maven.google.com")
//        maven("https://jitpack.io")
    }
}

dependencyResolutionManagement {
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven("https://maven.aliyun.com/repository/public")
//        maven("https://jitpack.io")
        google()
        mavenCentral()
    }
}

enableFeaturePreview("VERSION_CATALOGS")

// Generate type safe accessors when referring to other projects eg.
// Before: implementation(project(":feature-album"))
// After: implementation(projects.featureAlbum)
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Music"

include(":app")
include(":library-mvvm")
include(":library-base")
include(":library-ui")
include(":library-router-service")
include(":library-network")
include(":library-common")
include(":library-database")
include(":library-theme")
include(":module-main")
include(":module-user")
include(":module_local")