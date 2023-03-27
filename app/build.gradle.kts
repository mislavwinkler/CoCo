import Dependencies.Compose
import Dependencies.Hilt
import Dependencies.Androidx
import Dependencies.Androidx.Core.Core
import Dependencies.Androidx.Lifecycle.Lifecycle
import Dependencies.Androidx.Test.Test
import Dependencies.Firebase
import Dependencies.Junit
import Dependencies.Kotlinx
import extensions.*

plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.Kotlin.android)
    kotlin(Plugins.Kotlin.kapt)
    id(Plugins.hilt)
    id(Plugins.googleServices)
}

android {
    namespace = "com.diplomski.mucnjak.coco"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.diplomski.mucnjak.coco"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    Androidx {
        Core {
            implementations(
                coreKtx,
            )
        }

        Lifecycle {
            implementations(
                lifecycleRuntimeKtx,
                lifecycleViewmodelKtx,
                lifecycleLivedataKtx
            )
        }
        Test {
            androidTestImplementations(
                junit,
            )
        }
    }

    Junit {
        testImplementations(
            junit,
        )
    }
    Hilt {
        implementations(
            hiltAndroid,
            hiltNavigationCompose,
        )
        kapts(
            hiltAndroidCompiler,
        )
    }

    Compose {
        implementations(
            ui,
            uiToolingPreview,
            material,
            activity,
            viewModel,
            navigation,
        )
        debugImplementations(
            uiTooling,
            uiTestManifest,
        )
        androidTestImplementations(
            uiTestJunit,
        )
    }

    Firebase {
        implementations(
            firebaseFirestore,
        )
    }

    Kotlinx {
        implementations(
            kotlinxCoroutinesPlayServices
        )
    }
}