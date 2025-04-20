import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.maven.publish)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true

            export(libs.decompose.decompose)
            export(libs.essenty.lifecycle)
            export(libs.essenty.backhandler)
        }
    }

    sourceSets.all {
        languageSettings {
            optIn("androidx.compose.runtime.ExperimentalComposeApi")
            optIn("com.arkivanov.decompose.ExperimentalDecomposeApi")
            optIn("com.arkivanov.decompose.DelicateDecomposeApi")
            optIn("kotlin.ExperimentalMultiplatform")
            enableLanguageFeature("ExpectActualClasses")

        }
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    applyDefaultHierarchyTemplate {
        common {
            withIos()
            withAndroidTarget()
        }
    }

    sourceSets {
        @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            api(libs.kotlinx.datetime)

            api(libs.koin.core)
            api(libs.koin.compose)
            api(libs.koin.compose.vm)

            api(libs.decompose.decompose)
            api(libs.decompose.extensions.compose)
            api(libs.decompose.extensions.compose.experimental)

            api(libs.essenty.lifecycle)
            api(libs.essenty.backhandler)

            implementation(compose.ui)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.materialIconsExtended)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)

            api(compose.components.resources)
            implementation(libs.coil3.compose)
            implementation(libs.coil3.svg)
            implementation(libs.coil3.network.ktor)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }

        androidMain.dependencies {
//            koin
            api(libs.koin.android)

            implementation(libs.ktor.client.okhttp)

            implementation(libs.android.material)
            implementation(libs.activity.ktx)
            implementation(libs.activity.compose)
            implementation(libs.compose.ui.tooling)



        }

        androidUnitTest.dependencies {
            implementation(libs.junit.jupiter)
            implementation(libs.mockk)
            implementation(libs.coroutines.test)
            implementation(libs.slf4j.simple)
        }
    }

    jvmToolchain(17)
}

compose.resources {
    publicResClass = true
}


android {
    compileSdk = libs.versions.androidCompileSdk.get().toInt()
    namespace = "mk.digital.kmpsample.shared"

    defaultConfig {
        minSdk = libs.versions.androidMinSdk.get().toInt()
        vectorDrawables.useSupportLibrary = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
if (project.hasProperty("configuration")) {
    tasks.register("assembleXCFramework") {
        group = "build"
        description =
            "📦 Assembles an XCFramework for iOS. Use -Pconfiguration=Debug|Release|Internal (default: Debug)"

        val config = (project.findProperty("configuration") as String?)
            ?.replaceFirstChar(Char::uppercaseChar)
            ?: throw GradleException("❌ Missing -Pconfiguration=BuildType (e.g., Debug, Release, Internal)")

        val effectiveBuildType = if (config == "Internal") "Debug" else config

        dependsOn(
            "link${effectiveBuildType}FrameworkIosArm64",
            "link${effectiveBuildType}FrameworkIosX64"
        )

        doLast {
            val outputDir = layout.buildDirectory.dir("xcframework/$config").get().asFile
            val outputFramework = outputDir.resolve("shared-$config.xcframework")


            if (outputFramework.exists()) {
                println("🗑 Removing existing XCFramework: $outputFramework")
                outputFramework.deleteRecursively()
            }

            outputDir.mkdirs()

            val frameworks = listOf(
                layout.buildDirectory.dir("bin/iosArm64/${effectiveBuildType.lowercase()}Framework/shared.framework")
                    .get().asFile,
                layout.buildDirectory.dir("bin/iosX64/${effectiveBuildType.lowercase()}Framework/shared.framework")
                    .get().asFile
            )

            println("✅ Assembling shared-$config.xcframework (effective build: $effectiveBuildType)")

            project.exec {
                commandLine(
                    "xcodebuild",
                    "-create-xcframework",
                    "-framework", frameworks[0].absolutePath,
                    "-framework", frameworks[1].absolutePath,
                    "-output", outputFramework.absolutePath
                )
            }
        }
    }

    tasks.register<Copy>("linkXCFrameworkToIosApp") {
        dependsOn("assembleXCFramework")
        outputs.upToDateWhen { false }

        val config = (project.findProperty("configuration") as String?)?.lowercase()
            ?: throw GradleException("❌ Missing -Pconfiguration=Debug|Internal|Release")

        val configCapitalized = config.replaceFirstChar(Char::uppercaseChar)
        val frameworkName = "shared-$configCapitalized.xcframework"

        val sourceDir = layout.buildDirectory.dir("xcframework/$configCapitalized/$frameworkName")
        val targetDir = rootProject.layout.projectDirectory.dir("iosApp/Frameworks/$frameworkName")

        println("📦 Source dir: ${sourceDir.get().asFile.absolutePath}")
        println("📂 Target dir: ${targetDir.asFile.absolutePath}")

        targetDir.asFile.parentFile.mkdirs()
        targetDir.asFile.mkdirs()

        println("🔗 Copying $frameworkName to iosApp/Frameworks/$frameworkName")

        from(sourceDir)
        into(targetDir)
    }

    tasks.register<Copy>("linkXCFrameworkToIosAppForArchive") {
        dependsOn("assembleXCFramework")
        val config = (project.findProperty("configuration") as String?)?.lowercase()
            ?: throw GradleException("❌ Missing -Pconfiguration=Debug|Internal|Release")

        val configCapitalized = config.replaceFirstChar(Char::uppercaseChar)
        val frameworkName = "shared-$configCapitalized.xcframework"

        val sourceDir = layout.buildDirectory.dir("xcframework/$configCapitalized/$frameworkName")
        val targetDir =
            rootProject.layout.projectDirectory.dir("iosApp/$frameworkName") // do root iosApp

        println("🔗 Copying $frameworkName to iosApp/$frameworkName (for Archive)")

        from(sourceDir)
        into(targetDir)
    }
}

