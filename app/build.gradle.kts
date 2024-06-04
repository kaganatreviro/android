plugins {
    alias(libs.plugins.agp.application)
    alias(libs.plugins.kotlin.android)
    id(libs.plugins.secretsGradle.get().pluginId)
}

android {
    namespace = "com.example.happyhours"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.happyhours"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(":features:auth:presentation"))
    implementation(project(":features:main:presentation"))
    implementation(project(":data"))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.ui.splashscreen)

    //Navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    //DI
    implementation(libs.koin.android)
    
    //Bundle
    implementation(libs.bundles.ui)

    //Test
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.junit.ext)
    androidTestImplementation(libs.test.espresso)
}