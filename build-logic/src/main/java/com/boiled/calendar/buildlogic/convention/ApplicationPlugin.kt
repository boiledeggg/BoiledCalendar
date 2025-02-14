package com.boiled.calendar.buildlogic.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.boiled.calendar.buildlogic.dsl.androidApplicationExtension
import com.boiled.calendar.buildlogic.dsl.configureAndroidLibrary
import com.boiled.calendar.buildlogic.dsl.libs
import com.boiled.calendar.buildlogic.dsl.version
import com.boiled.calendar.buildlogic.primitive.CommonAndroidPlugin
import com.boiled.calendar.buildlogic.primitive.ComposePlugin
import com.boiled.calendar.buildlogic.primitive.KotlinPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

class ApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            apply<KotlinPlugin>()
            apply<CommonAndroidPlugin>()
            apply<ComposePlugin>()
            configureAndroidLibrary()

            extensions.getByType<ApplicationExtension>().apply {
                composeOptions {
                    kotlinCompilerExtensionVersion =
                        libs.findVersion("kotlinCompilerExtensionVersion").get().requiredVersion
                }
                buildFeatures.compose = true
            }

            androidApplicationExtension {
                namespace = "com.boiled.calendar"

                defaultConfig {
                    applicationId = libs.version("applicationId")
                    versionCode = libs.version("versionCode").toInt()
                    versionName = libs.version("versionName")
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
}