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
        withJava()
        tasks.withType<Test> {
            useJUnitPlatform()
        }
    }
    js(IR) { library() }
    //nativeTargets(true)
	linuxTargets(true)
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(kotlinx.serialization.core)
                api(projects.kollectionsInteroperable)
                api(projects.kronoApi)
                api(projects.identifierCore)
                api(projects.kashMoney)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlinx.serialization.json)
                implementation(asoft.expect.core)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.stdlib.get(),
    description = "A platform agnostic representation of payments and requests"
)