buildscript {
    repositories {
        google()
    }
    dependencies {
        val nav_version = "2.7.6"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
    id("com.google.dagger.hilt.android") version "2.46" apply false
}
//buildscript {
//    repositories {
//        google()
//        mavenCentral()
//    }
//
//    dependencies {
//        classpath ("com.android.tools.build:gradle:8.2.2")
//        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.22")
//
//        val nav_version = "2.7.6"
//        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
//        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.48.1")
//
//    }
//}
//// Top-level build file where you can add configuration options common to all sub-projects/modules.
//plugins {
//    id("com.android.application") version "8.2.1" apply false
//    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
//    id("com.google.dagger.hilt.android") version "2.46" apply false
//
//}
//
//
//
