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
    api(libs.bundles.paging)

    api(libs.coil)
    api(libs.coil.compose)
    api(libs.coil.gif)
    api(libs.coil.video)
}