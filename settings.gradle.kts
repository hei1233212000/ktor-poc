rootProject.name = "ktor-poc"

pluginManagement {
    val kotlin_version: String by settings

    plugins {
        kotlin("jvm") version kotlin_version
    }
}
