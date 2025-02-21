import com.boiled.calendar.buildlogic.dsl.setNameSpace
import com.vanniktech.maven.publish.SonatypeHost
import java.util.Properties

plugins {
    id("com.boiled.calendar.buildlogic.convention.compose")
    id("com.boiled.calendar.buildlogic.primitive.test")
    alias(libs.plugins.maven)
}

val properties = Properties().apply {
    load(rootProject.file("local.properties").inputStream())
}

android {
    setNameSpace("calendar.compose")

    buildTypes {
        debug {
            buildConfigField(
                "String",
                "mavenCentralUsername",
                properties.getProperty("mavenCentralUsername")
            )

            buildConfigField(
                "String",
                "mavenCentralPassword",
                properties.getProperty("mavenCentralPassword")
            )

            buildConfigField(
                "String",
                "signingKeyId",
                properties.getProperty("signing.keyId")
            )

            buildConfigField(
                "String",
                "signingPassword",
                properties.getProperty("signing.password")
            )

            buildConfigField(
                "String",
                "signingSecretKeyRingFile",
                properties.getProperty("signing.secretKeyRingFile")
            )
        }
    }
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    signAllPublications()

    coordinates("io.github.boiledeggg", "boiled-calendar", libs.versions.versionName.get())

    pom {
        name.set("Boiled Calendar")
        description.set("Compose Calendar")
        url.set("https://github.com/boiledeggg/BoiledCalendar")
        inceptionYear.set("2025")

        licenses {
            license {
                name.set("MIT License")
                url.set("https://github.com/boiledeggg/BoiledCalendar/blob/main/LICENSE")
            }
        }

        developers {
            developer {
                id.set("LeeSeokJun")
                name.set("BoiledEgg")
                email.set("https://github.com/boiledeggg")
            }
        }

        scm {
            url.set("https://github.com/boiledeggg/BoiledCalendar")
            connection.set("scm:git:github.com/boiledeggg/BoiledCalendar.git")
            developerConnection.set("scm:git:ssh://github.com:boiledeggg/BoiledCalendar.git")
        }
    }
}



