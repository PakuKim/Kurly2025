import kr.co.kurly.remote

plugins {
    alias(libs.plugins.kurly.android.library)
    alias(libs.plugins.kurly.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "kr.co.kurly.core.remote"

    defaultConfig {
        consumerProguardFiles("consumer-proguard-rules.pro")
    }

    flavorDimensions.add("environment")
    productFlavors {
        create("dev") {
            dimension = "environment"
            buildConfigField("String", "BASE_URL", "\"https://kurly.com/\"")
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