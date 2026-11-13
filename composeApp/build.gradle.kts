import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    kotlin("multiplatform") version "2.4.20"
    id("org.jetbrains.compose") version "1.12.0"
    id("org.jetbrains.kotlin.plugin.compose") version "2.4.20"
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            commonWebpackConfig {
                outputFileName = "zoomopticals.js"
            }
        }
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation("org.jetbrains.compose.components:components-resources:1.12.0")
        }
        wasmJsMain.dependencies {
            implementation("org.jetbrains.compose.runtime:runtime:1.12.0")
            implementation("org.jetbrains.compose.foundation:foundation:1.12.0")
            implementation("org.jetbrains.compose.material3:material3:1.12.0-alpha03")
            implementation("org.jetbrains.compose.ui:ui:1.12.0")
        }
    }
}

compose.resources {
    packageOfResClass = "com.zoomopticals.site"
}
