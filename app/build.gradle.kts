plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.joiefull"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.joiefull"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("androidx.core:core-splashscreen:1.0.1")

    // Retrofit dependencies
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    implementation ("com.google.code.gson:gson:2.10.1")

    //okhttp3 dependency

    implementation ("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")

    implementation ("androidx.fragment:fragment-ktx:1.8.4")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:1.8.4")



    //COIL
    implementation("io.coil-kt:coil-compose:2.4.0")

    //Google Fonts
    implementation ("androidx.compose.ui:ui-text-google-fonts:1.7.6")

    // Compose Navigationdependency
    implementation ("androidx.navigation:navigation-compose:2.8.5")


    implementation("io.insert-koin:koin-android:4.0.0") // Koin for Android
    implementation("io.insert-koin:koin-androidx-compose:4.0.0") // Koin for Jetpack Compose


    // Compose animation dependency

    implementation(platform("androidx.compose:compose-bom:1.5.2")) // Add or update this line
    implementation("androidx.compose.ui:ui:1.5.2") // Update the UI library
    implementation("androidx.compose.animation:animation:1.5.2") // Add this to explicitly use the latest animation library
    implementation("androidx.compose.material:material-icons-extended:1.5.2")


    implementation("androidx.compose.material3:material3:1.3.1")
    implementation("androidx.compose.material3:material3-window-size-class:1.3.1")
    implementation("androidx.compose.material3:material3-adaptive-navigation-suite:1.4.0-alpha05")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
// Allow references to generated code
kapt {
    correctErrorTypes = true
}