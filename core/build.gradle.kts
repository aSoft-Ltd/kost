plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    jvm {
        withJava()
        library()
        tasks.withType<Test> {
            useJUnitPlatform()
        }
    }
    js(IR) { library() }
    //nativeTargets(true)
//    linuxTargets(true)
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.koncurrent.later.core)
                api(kotlinx.serialization.core)
                api(libs.kollections.interoperable)
                api(projects.beeCore)
                api(libs.krono.kotlinx)
                api(libs.identifier.core)
                api(libs.identifier.comm)
                api(libs.kash.money)
                api(projects.kommerceCore)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlinx.serialization.json)
                implementation(libs.kommander.core)
            }
        }
    }
}

aSoftOSSLibrary(
    version = libs.versions.asoft.get(),
    description = "A platform agnostic representation of payments and requests"
)