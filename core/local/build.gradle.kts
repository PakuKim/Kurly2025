import kr.co.kurly.local

plugins {
    alias(libs.plugins.kurly.android.library)
    alias(libs.plugins.kurly.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "kr.co.kurly.core.local"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.data)

    local()
}