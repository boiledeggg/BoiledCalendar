package com.boiled.calendar.buildlogic.convention

import com.android.build.api.dsl.LibraryExtension
import com.boiled.calendar.buildlogic.dsl.configureAndroidLibrary
import com.boiled.calendar.buildlogic.primitive.ComposePlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.getByType

class ComposePlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {

        apply<FeaturePlugin>()
        apply<ComposePlugin>()

        extensions.getByType<LibraryExtension>().apply {
            buildFeatures.compose = true
        }

        configureAndroidLibrary()
    }
}