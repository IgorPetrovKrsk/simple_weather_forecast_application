// Top-level build file where you can add configuration options common to all sub-projects/modules.
//buildscript{
//    dependencies{
//        classpath(libs.hilt.android.gradle.plugin)
//    }
//}
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
    alias(libs.plugins.googledevtoolsksp) apply false
    id("com.google.dagger.hilt.android") version "2.50" apply false
}