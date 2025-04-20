import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    alias(libs.plugins.compose.compiler)
//    id("com.google.devtools.ksp")
    id("maven-publish")
//    id("kotlinx-serialization")
//    alias(libs.plugins.kmmbridge)
}

configureCompilerOptions()

dependencies {
//    implementation(platform(libs.firebase.bom))
}

kotlin {
    androidTarget()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true

            export(libs.decompose.decompose)
        }
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    applyDefaultHierarchyTemplate {
        common {
            group("mobile") {
                withIos()
                withAndroidTarget()
            }
        }
    }

    sourceSets {
        @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
        commonMain {
            dependencies {
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.serialization)
                api(libs.kotlinx.datetime)

                api(libs.koin.core)
                api(libs.koin.compose)
                api(libs.koin.compose.vm)

                api(libs.decompose.decompose)
                api(libs.decompose.extensions.compose)
                api(libs.decompose.extensions.compose.experimental)
//                api(libs.essenty.lifecycle)
//                api(libs.essenty.backhandler)

                implementation(compose.ui)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.materialIconsExtended)

                api(compose.components.resources)
                implementation(libs.coil3.compose)
                implementation(libs.coil3.network.ktor)
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val mobileMain by getting {
            dependencies {
//                api(libs.firebase.mpp.auth)

                implementation(libs.permissions)
                implementation(libs.permissions.notifications)
                implementation(libs.permissions.compose)
            }
        }

        iosMain {
            dependsOn(mobileMain)
        }

        androidMain {
            dependsOn(mobileMain)

            dependencies {
//                api(libs.androidx.lifecycle.viewmodel.ktx)
                implementation(libs.okhttp)
                implementation(libs.okhttp.coroutines)
                implementation(libs.okhttp.logging.interceptor)
                api(libs.coil.base)
                api(libs.koin.android)
                api(libs.koin.compose)
                api(libs.okio)
                implementation(libs.coil.svg)

                api(libs.androidx.datastore)
                api(libs.androidx.datastore.preferences)
                api(libs.multiplatform.settings.datastore)
                implementation(libs.googleid)
            }
        }
    }
}

compose.resources {
    publicResClass = true
}


android {
    compileSdk = AndroidSdk.compile
    namespace = "mk.digital.kmpsample.common"

    sourceSets["main"].manifest.srcFile("src/main/AndroidManifest.xml")
//    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = AndroidSdk.min
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }
}

dependencies {
    coreLibraryDesugaring(libs.desugar)
}

tasks.register("assembleXCFramework") {
    group = "build"
    description = "Assembles an XCFramework for the shared module."

    dependsOn(
        "linkDebugFrameworkIosArm64",
        "linkDebugFrameworkIosX64",
    )

    doLast {
        val outputDir = layout.buildDirectory.dir("xcframework").get().asFile
        val outputFramework = outputDir.resolve("shared.xcframework")

//        clean up previous framework
        if (outputFramework.exists()) {
            println("Removing existing built XCFramework: $outputFramework")
            outputFramework.deleteRecursively()
        }

        outputDir.mkdirs()

        val frameworks = listOf(
            layout.buildDirectory.dir("bin/iosArm64/debugFramework/shared.framework").get().asFile,
            layout.buildDirectory.dir("bin/iosX64/debugFramework/shared.framework").get().asFile,
        )

        exec {
            this.commandLine(
                "xcodebuild", "-create-xcframework",
                "-framework", frameworks[0].absolutePath,
                "-framework", frameworks[1].absolutePath,
                "-output", outputDir.resolve("shared.xcframework").absolutePath
            )
        }
    }
}
