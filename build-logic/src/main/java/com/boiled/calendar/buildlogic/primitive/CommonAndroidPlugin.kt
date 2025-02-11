package com.boiled.calendar.buildlogic.primitive

import com.android.build.gradle.BaseExtension
import com.boiled.calendar.buildlogic.dsl.implementation
import com.boiled.calendar.buildlogic.dsl.library
import com.boiled.calendar.buildlogic.dsl.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class CommonAndroidPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        extensions.getByType<BaseExtension>().apply {
            defaultConfig {
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }
            buildFeatures.apply {
                buildConfig = true
            }
        }
        dependencies {
            implementation(libs.library("androidx-core-ktx"))
            implementation(libs.library("androidx-appcompat"))
            implementation(libs.library("material"))
        }
    }
}