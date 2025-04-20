plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
//    id("com.google.gms.google-services")
//    id("com.google.firebase.crashlytics")
//    id("io.github.takahirom.roborazzi")
//    alias(libs.plugins.screenshot)
}

configureCompilerOptions()

android {
    compileSdk = AndroidSdk.compile
    namespace = "mk.digital.kmpsample"

    defaultConfig {
        applicationId = "mk.digital.kmpsample.MyApplication"
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
        versionCode = 1
        versionName = "1.0"
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    packaging {
        resources {
            excludes += listOf(
                "/META-INF/AL2.0",
                "/META-INF/LGPL2.1",
                "/META-INF/versions/**"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}

kotlin {
    sourceSets.all {
        languageSettings {
            optIn("androidx.compose.material.ExperimentalMaterialApi")
            optIn("kotlin.RequiresOptIn")
            optIn("androidx.compose.runtime.ExperimentalComposeApi")
        }
    }
}

dependencies {
    implementation(project(":shared"))
    coreLibraryDesugaring(libs.desugar)
    implementation(libs.activity.compose)

    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.material)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.coil.compose)
}