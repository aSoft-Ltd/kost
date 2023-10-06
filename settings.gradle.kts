pluginManagement {
    includeBuild("../build-logic")
}

plugins {
    id("multimodule")
}

fun includeSubs(base: String, path: String = base, vararg subs: String) {
    subs.forEach {
        include(":$base-$it")
        project(":$base-$it").projectDir = File("$path/$it")
    }
}

listOf(
    "kommander", "kollections", "kevlar", "neat", "identifier-api", "identifier-client",
    "kash-api", "kash-client", "krono-core", "krono-client", "bee", "koncurrent", "kommerce", "symphony"
).forEach { includeBuild("../$it") }

rootProject.name = "kost"

includeSubs("kost", "../kost", "core", "dtos", "presenters")
includeSubs("kost-api", "../kost/api", "core")
includeSubs("kost-sdk", "../kost/sdk", "core")
