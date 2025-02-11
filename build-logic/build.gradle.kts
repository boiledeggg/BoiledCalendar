plugins {
    `kotlin-dsl`
}

group = "com.boiled.calendar.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    compileOnly(libs.android.gradle)
    compileOnly(libs.kotlin.gradle)
    compileOnly(libs.compose.compiler.gradle)
}

gradlePlugin {
    plugins {
        register("com.boiled.calendar.buildlogic.primitive.CommonAndroidPlugin") {
            id = "com.boiled.calendar.buildlogic.primitive.common"
            implementationClass = "com.boiled.calendar.buildlogic.primitive.CommonAndroidPlugin"
        }

        register("com.boiled.calendar.buildlogic.primitive.ComposePlugin") {
            id = "com.boiled.calendar.buildlogic.primitive.compose"
            implementationClass = "com.boiled.calendar.buildlogic.primitive.ComposePlugin"
        }

        register("com.boiled.calendar.buildlogic.primitive.KotlinPlugin") {
            id = "com.boiled.calendar.buildlogic.primitive.kotlin"
            implementationClass = "com.boiled.calendar.buildlogic.primitive.KotlinPlugin"
        }

        register("com.boiled.calendar.buildlogic.primitive.TestPlugin") {
            id = "com.boiled.calendar.buildlogic.primitive.test"
            implementationClass = "com.boiled.calendar.buildlogic.primitive.TestPlugin"
        }

        register("com.boiled.calendar.buildlogic.convention.ApplicationPlugin") {
            id = "com.boiled.calendar.buildlogic.convention.application"
            implementationClass = "com.boiled.calendar.buildlogic.convention.ApplicationPlugin"
        }

        register("com.boiled.calendar.buildlogic.convention.ComposePlugin") {
            id = "com.boiled.calendar.buildlogic.convention.compose"
            implementationClass = "com.boiled.calendar.buildlogic.convention.ComposePlugin"
        }

        register("com.boiled.calendar.buildlogic.convention.FeaturePlugin") {
            id = "com.boiled.calendar.buildlogic.convention.feature"
            implementationClass = "com.boiled.calendar.buildlogic.convention.FeaturePlugin"
        }
    }
}