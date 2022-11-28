pluginManagement {
    enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        mavenCentral()
        google()
        gradlePluginPortal()
    }

    dependencyResolutionManagement {
        versionCatalogs {
            file("gradle/versions").listFiles().map { it.nameWithoutExtension }.forEach {
                create(it) { from(files("gradle/versions/$it.toml")) }
            }
        }
    }
}

val tmp = 0

fun includeRoot(name: String, path: String) {
    include(":$name")
    project(":$name").projectDir = File(path)
}

fun includeSubs(base: String, path: String = base, vararg subs: String) {
    subs.forEach {
        include(":$base-$it")
        project(":$base-$it").projectDir = File("$path/$it")
    }
}

rootProject.name = "kost"

// dependencies
includeSubs("functions", "../functions", "core")
includeSubs("expect", "../expect", "core", "coroutines")
includeSubs("kollections", "../kollections", "interoperable")
includeSubs("formatter", "../formatter", "core")

includeBuild("../kash/kash-generator")
includeSubs("kash", "../kash", "currency", "money")
includeSubs("krono", "../krono", "api", "kotlinx")

includeSubs("identifier", "../identifier", "core")
includeSubs("kost", ".", "core")