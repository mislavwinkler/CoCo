object Dependencies {
    object Compose : DependencyGrouping<Compose> {
        const val ui: String = "androidx.compose.ui:ui:${Versions.compose}"
        const val uiToolingPreview: String = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
        const val material: String = "androidx.compose.material:material:${Versions.compose}"

        const val activity: String = "androidx.activity:activity-compose:${Versions.composeActivity}"
        const val viewModel: String =
            "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewmodel}"
        const val navigation: String =
            "androidx.navigation:navigation-compose:${Versions.composeNavigation}"

        const val uiTooling: String = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val uiTestManifest: String = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"

        const val uiTestJunit: String = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    }

    object Hilt : DependencyGrouping<Hilt> {
        const val hiltAndroid: String = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltNavigationCompose: String =
            "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}"

        const val hiltAndroidCompiler: String = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    }

    object Androidx : DependencyGrouping<Androidx> {
        object Core {
            fun Androidx.Core(grouping: Core.() -> Unit): Unit = grouping(Core)

            const val coreKtx: String = "androidx.core:core-ktx:${Versions.coreKtx}"
            const val coreSplashScreen: String =
                "androidx.core:core-splashscreen:${Versions.coreSplashScreen}"
        }

        object Lifecycle {
            fun Androidx.Lifecycle(grouping: Lifecycle.() -> Unit): Unit = grouping(Lifecycle)

            const val lifecycleRuntimeKtx: String =
                "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"
            const val lifecycleViewmodelKtx: String =
                "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewmodelKtx}"
            const val lifecycleLivedataKtx: String =
                "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecyclycleLivedataKtx}"
        }

        object Test {
            fun Androidx.Test(grouping: Test.() -> Unit): Unit = grouping(Test)

            const val junit: String = "androidx.test.ext:junit:${Versions.androidxJunit}"
        }
    }

    object Junit : DependencyGrouping<Junit> {

        const val junit: String = "junit:junit:${Versions.junit}"
    }

    object Firebase : DependencyGrouping<Firebase> {
        const val firebaseBom: String = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
        const val firebaseFirestore: String = "com.google.firebase:firebase-firestore-ktx"
        const val firebaseStorage: String = "com.google.firebase:firebase-storage-ktx"
        const val firebaseAnalytics: String = "com.google.firebase:firebase-analytics-ktx"
    }

    object Kotlinx : DependencyGrouping<Kotlinx> {
        const val kotlinxCoroutinesPlayServices: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.kotlinxCoroutinesPlayServices}"
    }

    object Glide : DependencyGrouping<Glide> {
        const val glideCompose: String = "com.github.bumptech.glide:compose:${Versions.glideCompose}"
    }

}

interface DependencyGrouping<T> {
    @Suppress("UNCHECKED_CAST")
    operator fun invoke(grouping: T.() -> Unit): Unit = grouping(this as T)
}
