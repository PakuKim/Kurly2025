import kr.co.kurly.remote

plugins {
    alias(libs.plugins.kurly.android.library)
    alias(libs.plugins.kurly.android.hilt)
    id("com.google.devtools.ksp")
    id("kotlinx-serialization")
}

android {
    namespace = "kr.co.kurly.core.remote"

    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.mockserver)

    remote()
}