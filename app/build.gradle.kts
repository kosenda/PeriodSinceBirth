@file:Suppress("UnstableApiUsage")

val ktlint: Configuration by configurations.creating
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id("com.google.android.gms.oss-licenses-plugin")
}

android {
    namespace = "ksnd.periodsincebirth"
    compileSdk = 33

    defaultConfig {
        applicationId = "ksnd.periodsincebirth"
        minSdk = 26
        targetSdk = 33
        versionCode = 2
        versionName = "0.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary =  true
        }
    }

    buildTypes {
        release {
            isShrinkResources = true
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose =  true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.1"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
        unitTests.all { it.jvmArgs("-noverify") }
    }
}

dependencies {
    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.0") {
            because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
        }
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0") {
            because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
        }
    }
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.compose.ui:ui:1.3.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.3")
    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.3.3")
    debugImplementation("androidx.compose.ui:ui-tooling:1.3.3")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.3.3")

    // ktlint
    ktlint("com.pinterest:ktlint:0.48.2") {
        attributes {
            attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
        }
    }

    // Material3
    implementation("androidx.compose.material3:material3:1.1.0-alpha06")

    // Redux-Kotlin
    implementation("org.reduxkotlin:redux-kotlin-compose-jvm:0.6.0")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.45")
    kapt("com.google.dagger:hilt-compiler:2.44.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Timber
    implementation("com.jakewharton.timber:timber:5.0.1")

    // Truth
    testImplementation("com.google.truth:truth:1.1.3")

    // accompanist
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.5.3")

    // dataStore preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Robolectric environment
    testImplementation("androidx.test:core:1.5.0")
    testImplementation("org.robolectric:robolectric:4.9.2")

    // Test Coroutine
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

    // OSS Licenses Gradle Plugin
    implementation("com.google.android.gms:play-services-oss-licenses:17.0.0")

    // Google Fonts
    implementation("androidx.compose.ui:ui-text-google-fonts:1.3.3")

    // Splash Screen
    implementation("androidx.core:core-splashscreen:1.0.0")
}

tasks.create<JavaExec>("ktlintCheck") {
    description = "Check Kotlin code style."
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    args = listOf("src/**/*.kt")
}

tasks.create<JavaExec>("ktlintFormatting") {
    description = "Fix Kotlin code style deviations."
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    args("-F", "src/**/*.kt")
    jvmArgs("--add-opens", "java.base/java.lang=ALL-UNNAMED")
}
