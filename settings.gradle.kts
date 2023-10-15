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
        // You can add other repositories here if needed for dependency resolution
    }
}

rootProject.name = "MyNotes"
include(":app")
