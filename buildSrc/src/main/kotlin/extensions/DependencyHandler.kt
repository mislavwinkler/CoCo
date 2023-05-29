package extensions

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.kapts(vararg kapts: String) {
    kapts.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementations(vararg implementations: Any) {
    implementations.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementations(vararg androidTestImplementations: String) {
    androidTestImplementations.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementations(vararg testImplementations: String) {
    testImplementations.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.debugImplementations(vararg debugImplementations: String) {
    debugImplementations.forEach { dependency ->
        add("debugImplementation", dependency)
    }
}
