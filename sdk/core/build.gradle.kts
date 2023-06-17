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
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(projects.kostApiCore)
                api(projects.kostPresenters)
                api(projects.symphonyInputForm)
                api(projects.symphonyInputList)
                api(projects.symphonyInputChoice)
                api(projects.symphonyInputKrono)
                api(projects.symphonyInputKash)
                api(projects.symphonyInputNumber)
                api(projects.symphonyInputChoice)
                api(projects.symphonyCollections)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlinx.serialization.json)
                implementation(projects.kommanderCore)
            }
        }
    }
}

aSoftOSSLibrary(
    version = asoft.versions.root.get(),
    description = "A platform agnostic representation of payments and requests"
)