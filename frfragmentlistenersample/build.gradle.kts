/*
 * Copyright (c) 2021 SallyLueNoa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

val domain = "org.fog-rock"
val release = "release"
val javaVersion = JavaVersion.VERSION_11
val Project.versionName: String get() = (this.findProperty("version.name") ?: "0.0.1-SNAPSHOT").toString()
val Project.versionCode: Int get() = (this.findProperty("version.code") ?: "1").toString().toInt()

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.app.gradle)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "${domain}.${project.name}".replace('-', '_')
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "${domain}.${project.name}".replace('-', '_')
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = project.versionCode
        versionName = project.versionName
        base {
            archivesName.set("${project.name}-${project.versionName}.${project.versionCode}")
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    signingConfigs {
        create(release) {
            storeFile = rootProject.file("sallyluenoa_sample.jks")
            storePassword = System.getenv("STORE_PASSWORD")
            keyAlias = System.getenv("KEY_ALIAS")
            keyPassword = System.getenv("KEY_PASSWORD")
        }
    }
    buildTypes {
        getByName(release) {
            signingConfig = signingConfigs.getByName(release)
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
    kotlinOptions {
        jvmTarget = javaVersion.toString()
    }
    viewBinding {
        enable = true
    }
    dataBinding {
        enable = true
    }
}

dependencies {
    // main
    implementation(project(":frfragmentlistener"))
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.fogrock.frextensions)
    implementation(libs.google.android.material)
    // androidTest
    androidTestImplementation(libs.bundles.androidx.test)
    androidTestImplementation(libs.androidx.test.espresso.intents)
}