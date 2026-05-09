plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.roadassist"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.example.roadassist"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }

    }
    buildFeatures {
        compose = true
        kotlinOptions {
            jvmTarget = libs.versions.jvmTarget.get()
        }
    }

    dependencies {

        // Activity + ViewModel
        implementation(libs.androidx.activity.compose)
        implementation(libs.androidx.lifecycle.viewmodel.compose)

        // AndroidX Core
        implementation(libs.androidx.core.ktx)
        implementation(libs.androidx.lifecycle.runtime.ktx)

        // Compose
        implementation(platform(libs.androidx.compose.bom))
        implementation(libs.bundles.compose.ui)
//        implementation(libs.androidx.compose.foundation)

        // Navigation
        implementation(libs.androidx.navigation.compose)

        // Animations
//        implementation(libs.androidx.compose.animation)

        // Unit Test
        testImplementation(libs.junit)

        // Android Test
        androidTestImplementation(platform(libs.androidx.compose.bom))
        androidTestImplementation(libs.bundles.android.test)
        androidTestImplementation(libs.bundles.compose.test)

        // Debug
        debugImplementation(libs.bundles.compose.debug)
    }
}