package com.boiled.calendar.buildlogic.convention

import com.boiled.calendar.buildlogic.dsl.configureAndroidLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class ComposePlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        apply<ComposePlugin>()
        apply<FeaturePlugin>()
        configureAndroidLibrary()
    }
}