package com.boiled.calendar.buildlogic.primitive

import com.android.build.gradle.BaseExtension
import com.boiled.calendar.buildlogic.dsl.implementation
import com.boiled.calendar.buildlogic.dsl.library
import com.boiled.calendar.buildlogic.dsl.libs
import com.boiled.calendar.buildlogic.dsl.testImplementation
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class KotlinPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(plugins) {
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.serialization")
                apply("org.jetbrains.kotlin.plugin.parcelize")
            }

            extensions.getByType<BaseExtension>().apply {
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }
            }

            tasks.withType<KotlinCompile> {
                compilerOptions.jvmTarget.set(JvmTarget.JVM_17)
            }

            dependencies{
                implementation(libs.library("kotlinx-coroutines-core"))
                implementation(libs.library("kotlinx-coroutines-android"))
                implementation(libs.library("kotlinx-immutable"))
                implementation(libs.library("kotlinx-serialization-json"))

                testImplementation(libs.library("kotlinx-coroutines-test"))
            }
        }


    }
}