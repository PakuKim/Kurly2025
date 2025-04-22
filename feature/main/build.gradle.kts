plugins {
    alias(libs.plugins.kurly.android.feature)
    alias(libs.plugins.kurly.android.library.compose)
    id("kotlin-parcelize")
}

android {
    namespace = "kr.co.kurly.feature.main"
}

dependencies {
    
}