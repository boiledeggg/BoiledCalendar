import com.boiled.calendar.buildlogic.dsl.implementation

plugins {
    id("com.boiled.calendar.buildlogic.convention.application")
    id("com.boiled.calendar.buildlogic.primitive.test")
}

android {
    namespace = "com.boiled.calendar"
}

dependencies {
    implementation(project(":calendar"))
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.datetime)
}