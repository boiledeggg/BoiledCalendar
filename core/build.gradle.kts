import com.boiled.calendar.buildlogic.dsl.implementation
import com.boiled.calendar.buildlogic.dsl.setNameSpace

plugins {
    id("com.boiled.calendar.buildlogic.convention.compose")
    id("com.boiled.calendar.buildlogic.primitive.test")
}

android {
    setNameSpace("calendar.core")
}

dependencies {
    implementation(libs.kotlinx.datetime)
}