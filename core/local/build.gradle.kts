import kr.co.kurly.local

plugins {
    alias(libs.plugins.kurly.android.library)
    alias(libs.plugins.kurly.android.hilt)
    id("androidx.room")
    id("com.google.devtools.ksp")
    id("kotlinx-serialization")
}

ksp {
    arg("room.generateKotlin", "true")
}

room {
    schemaDirectory("$projectDir/schemas")
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