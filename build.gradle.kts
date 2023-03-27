plugins {
    id(Plugins.androidApplication) version Versions.androidApplication apply false
    id(Plugins.kotlin) version Versions.kotlin apply false
    id(Plugins.hilt) version Versions.hilt apply false
    id(Plugins.googleServices) version Versions.googleServices apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
