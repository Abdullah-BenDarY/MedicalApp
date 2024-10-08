plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
//    id("kotlin-kapt")
//    id ("dagger.hilt.android.plugin")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.example.medicalapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.medicalapp"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    // ssp
    implementation("com.intuit.ssp:ssp-android:1.1.1")
    // sdp
    implementation("com.intuit.sdp:sdp-android:1.1.1")

    // Nav
    val nav_version = "2.7.7"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    // Coroutines
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")

    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.4")

    val retrofitVersion = "2.9.0"
    val  okhttp3Version = "4.5.0"
    // Retrofit
        implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
        implementation("com.squareup.okhttp3:logging-interceptor:$okhttp3Version")
        implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-android-compiler:2.48.1")


    // Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    //circle image
    implementation("de.hdodenhof:circleimageview:3.1.0")
    // animation
    implementation("com.airbnb.android:lottie:6.1.0")

}
kapt {
    correctErrorTypes = true
}