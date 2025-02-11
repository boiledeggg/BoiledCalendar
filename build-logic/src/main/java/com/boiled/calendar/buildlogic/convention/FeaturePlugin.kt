package com.boiled.calendar.buildlogic.convention

import com.android.build.api.dsl.LibraryExtension
import com.boiled.calendar.buildlogic.dsl.configureAndroidLibrary
import com.boiled.calendar.buildlogic.primitive.CommonAndroidPlugin
import com.boiled.calendar.buildlogic.primitive.KotlinPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class FeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
        }

        apply<KotlinPlugin>()
        apply<CommonAndroidPlugin>()
        configureAndroidLibrary()

        extensions.configure<LibraryExtension> {
            defaultConfig {
                consumerProguardFiles("consumer-rules.pro")
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
        }
    }
}
