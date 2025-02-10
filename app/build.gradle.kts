plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.movietracker"  // Define your package here
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.movietracker"
        minSdk = 21
        targetSdk = 35
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    viewBinding {
        enable = true
    }
    dataBinding{
        enable = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3" // ✅ Use the latest version
    }
    dependenciesInfo {
        includeInApk = true
        includeInBundle = true
    }
    buildToolsVersion = "35.0.1"
    kapt {
        arguments {
            arg("room.incremental", "true")
        }
        correctErrorTypes = true
    }

}

dependencies {
    // Core dependencies
    implementation(libs.androidx.compose.ui.ui)
    implementation(libs.androidx.material)
    implementation(libs.androidx.compose.ui.ui.tooling.preview)

    // Ensure the Compose Runtime is included
    implementation(libs.androidx.runtime)

    // Compose Compiler (should match your Compose version)
    kapt(libs.androidx.compiler)
    implementation(libs.androidx.lifecycle.livedata.ktx.v262)
    implementation(libs.androidx.core.ktx.v1120)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.kotlinx.coroutines.test)// Use latest version
    testImplementation(libs.mockk) // For MockK (or Mockito if you're using that)
    testImplementation(libs.junit) // If you're using JUnit 4
    testImplementation(libs.androidx.core.testing)

    // Retrofit for API calls
    implementation(libs.retrofit.v290)
    implementation(libs.converter.gson.v290)

    // Glide for image loading
    implementation(libs.glide)

    // Room for local database
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.lifecycle.viewmodel.android)
    implementation(libs.androidx.paging.common.android)
    testImplementation(libs.junit.junit2)
    //noinspection KaptUsageInsteadOfKsp
    kapt(libs.androidx.room.compiler)

    // ViewModel and LiveData
    implementation(libs.androidx.lifecycle.viewmodel.ktx.v270)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Navigation Component
    implementation(libs.androidx.navigation.fragment.ktx.v277)
    implementation(libs.androidx.navigation.ui.ktx.v277)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.androidx.hilt.lifecycle.viewmodel)
    kapt(libs.androidx.hilt.compiler)

    implementation(libs.kotlin.stdlib)
    kapt(libs.dagger.compiler)

    implementation(libs.androidx.room.ktx.v250)
}