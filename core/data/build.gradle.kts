plugins {
    alias(libs.plugins.kurly.android.library)
    alias(libs.plugins.kurly.android.hilt)
}

android {
    namespace = "kr.co.kurly.core.data"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.domain)
}