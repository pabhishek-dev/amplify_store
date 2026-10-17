plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.abhi.amplify_store"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.abhi.amplify_store"
        minSdk = 25
        targetSdk = 37
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }

    buildFeatures {
        compose = true
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

dependencies {

    // Foundational Android/Kotlin extensions and lifecycle-aware components.
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Jetpack Compose – UI Toolkit Declarative UI framework, Material design, typography, icons, and adaptive layouts.
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.core)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.ui.text.google.fonts)
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.compose.material3.window.size.class1)
    implementation(libs.androidx.lifecycle.process)

    // Navigation In-app screen navigation for Compose.
    implementation(libs.androidx.navigation.compose)
    implementation(libs.firebase.auth)
    implementation(libs.googleid)

    // Dependency Injection (Hilt) Compile-time DI container and Compose/Navigation integration.
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    // Local Persistence – Room SQLite ORM with Kotlin coroutines/Flow support.
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Local Persistence – DataStore Key-value preferences storage (modern replacement for SharedPreferences).
    implementation(libs.androidx.datastore.preferences)

    // Image Loading Async image loading with Compose bindings and network fetching.
    implementation(libs.coil.compose)
    implementation(libs.coil.network)

    // Logging Timber
    implementation(libs.timber)

    // Gson & OkHttp SSE
    implementation(libs.converter.gson)
    implementation(libs.okhttp.sse)

    // WorkManager Kotlin + Coroutines support
    implementation(libs.androidx.work.runtime.ktx)

    // ExoPlayer
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)
    implementation(libs.androidx.media3.ui.compose)

    // Play HLS STREAMS
    implementation(libs.androidx.media3.exoplayer.hls)

    // Modules
    implementation(project(":analytics"))
    implementation(project(":network"))
    implementation(project(":common"))


    // Unit Testing (JVM) JUnit 4/5 engines, launcher, and coroutine test utilities for local tests.
    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
    testRuntimeOnly(libs.junit.platform.launcher)
    testImplementation(libs.kotlinx.coroutines.test)

    // Instrumentation / UI Testing (Device) On-device tests including Espresso and Compose UI test harness.
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    // Debug-Only Tooling Compose preview/inspector and test manifest, included only in debug builds.
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

}
