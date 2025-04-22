plugins {
    alias(libs.plugins.kurly.android.library)
    alias(libs.plugins.kurly.android.hilt)
    id("kotlin-parcelize")
}

android {
    namespace = "kr.co.kurly.core.domain"
}

dependencies {
    implementation(projects.core.common)
}