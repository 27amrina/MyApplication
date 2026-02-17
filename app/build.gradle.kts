
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    //penambahan photo view
//    allprojects {
//        repositories {
//            maven{url ; "https://www.jitpack.io"};
//        };
//    };
//    buildscript {
//        repositories {
//            maven { url ; "https://www.jitpack.io" }
//        }
//    }


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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.material.v161)
    implementation(libs.glide)
    implementation(libs.material.v1100)
    implementation(libs.androidx.core.splashscreen)
//    implementation(libs.photoview.v200)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
//    androidTestImplementation("com.github.chrisbanes:photoview:2.0.0")
}