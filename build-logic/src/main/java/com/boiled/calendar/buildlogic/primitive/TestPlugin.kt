package com.boiled.calendar.buildlogic.primitive

import com.boiled.calendar.buildlogic.dsl.androidTestImplementation
import com.boiled.calendar.buildlogic.dsl.library
import com.boiled.calendar.buildlogic.dsl.libs
import com.boiled.calendar.buildlogic.dsl.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class TestPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                testImplementation(libs.library("junit"))
                testImplementation(libs.library("kotlin-test"))
                androidTestImplementation(libs.library("androidx-junit"))
                androidTestImplementation(libs.library("androidx-espresso-core"))
            }
        }
    }
}
