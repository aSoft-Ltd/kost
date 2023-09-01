plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    jvm { library() }
    js(IR) { library() }
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.kostApiCore)
                api(projects.kostPresenters)
                api(libs.symphony.input.choice)
//                api(projects.symphonyInputKrono)
//                api(projects.symphonyInputKash)
                api(libs.symphony.input.number)
                api(libs.symphony.collections)
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