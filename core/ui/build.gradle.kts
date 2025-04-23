plugins {
    alias(libs.plugins.kurly.android.library)
    alias(libs.plugins.kurly.android.library.compose)
}

android {
    namespace = "kr.co.kurly.core.ui"
}

dependencies {
    implementation(projects.core.common)

    api(libs.bundles.composes)
    api(libs.coil.compose)
}