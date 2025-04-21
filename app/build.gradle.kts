import kr.co.kurly.app

plugins {
    alias(libs.plugins.kurly.android.application)
    alias(libs.plugins.kurly.android.application.compose)
    alias(libs.plugins.kurly.android.hilt)
}

android {
    namespace = "kr.co.kurly"

    defaultConfig {
        applicationId = "kr.co.kurly"
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
        }

        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.remote)
    implementation(projects.core.ui)
    implementation(projects.core.local)
    implementation(projects.core.common)

    implementation(projects.feature.main)

    app()
}