// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.agp.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.agp.library) apply false
    alias(libs.plugins.kotlin.jvm) apply false
}

buildscript {
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
        //noinspection GradlePluginVersion
    }
}