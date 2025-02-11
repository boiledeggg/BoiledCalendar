package com.boiled.calendar.buildlogic.convention

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