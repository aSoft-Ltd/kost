plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    jvm {
        library();
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
                api(libs.bee.core)
                api(libs.krono.kotlinx)
                api(libs.kommerce.core)
                api(libs.kash.cents)
                api("tz.co.asoft:books-accounts-api-core:0.0.0")
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