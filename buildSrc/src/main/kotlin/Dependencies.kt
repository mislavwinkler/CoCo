object Dependencies {
    object Compose : DependencyGrouping<Compose> {
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"

        const val activity = "androidx.activity:activity-compose:${Versions.composeActivity}"
        const val viewModel =
            "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewmodel}"
        const val navigation =
            "androidx.navigation:navigation-compose:${Versions.composeNavigation}"

        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"

        const val uiTestJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    }

    object Hilt : DependencyGrouping<Hilt> {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}"

        const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    }

    object Androidx : DependencyGrouping<Androidx> {
        object Core {
            fun Androidx.Core(grouping: Core.() -> Unit) = grouping(Core)

            const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
            const val coreSplashScreen = "androidx.core:core-splashscreen:${Versions.coreSplashScreen}"
        }

        object Lifecycle {
            fun Androidx.Lifecycle(grouping: Lifecycle.() -> Unit) = grouping(Lifecycle)

            const val lifecycleRuntimeKtx =
                "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"
            const val lifecycleViewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewmodelKtx}"
            const val lifecycleLivedataKtx =  "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecyclycleLivedataKtx}"
        }

        object Test {
            fun Androidx.Test(grouping: Test.() -> Unit) = grouping(Test)

            const val junit = "androidx.test.ext:junit:${Versions.androidxJunit}"
        }
    }

    object Junit : DependencyGrouping<Junit> {

        const val junit = "junit:junit:${Versions.junit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    }

    object Firebase : DependencyGrouping<Firebase> {
        const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
        const val firebaseFirestore = "com.google.firebase:firebase-firestore-ktx"
        const val firebaseStorage = "com.google.firebase:firebase-storage-ktx"
    }

    object Kotlinx : DependencyGrouping<Kotlinx> {
        const val kotlinxCoroutinesPlayServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.kotlinxCoroutinesPlayServices}"
    }

    object Glide : DependencyGrouping<Glide> {
        const val glideCompose = "com.github.bumptech.glide:compose:${Versions.glideCompose}"
    }

}

interface DependencyGrouping<T> {
    @Suppress("UNCHECKED_CAST")
    operator fun invoke(grouping: T.() -> Unit) = grouping(this as T)
}
