plugins {
    alias(libs.plugins.kurly.android.library)
    alias(libs.plugins.kurly.android.hilt)
}

android {
    namespace = "kr.co.kurly.core.domain"
}

dependencies {
    implementation(projects.core.common)
}