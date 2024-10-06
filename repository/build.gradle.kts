plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.buildConfig)
    alias(libs.plugins.room)
    alias(libs.plugins.ksp)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "repository"
            isStatic = true
            // Required when using NativeSQLiteDriver
            linkerOpts.add("-lsqlite3")
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
        }
        commonMain.dependencies {
            implementation(libs.parcelize)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.bundles.ktor.common)
            implementation(libs.koin.core)
            implementation(libs.androidx.room.runtime)
            implementation(libs.sqlite.bundled)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

buildConfig {
    packageName = "hoa.kv.githubadmin.repository"
    useKotlinOutput { internalVisibility = true }
    buildConfigField(
        "String",
        "API_ENDPOINT",
        "\"https://api.github.com\""
    )
}

android {
    namespace = "hoa.kv.githubadmin.repository"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
}

room {
    schemaDirectory("$projectDir/schemas")
}
