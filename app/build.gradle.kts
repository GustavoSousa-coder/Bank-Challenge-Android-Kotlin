import org.gradle.kotlin.dsl.coreLibraryDesugaring

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.bankchellengemobileapp"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.example.bankchellengemobileapp"
        minSdk = 24
        //noinspection OldTargetApi
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11

        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    flavorDimensions += "version"
    productFlavors {
        create("demo") {
            dimension = "version"
            applicationIdSuffix = ".demo"
            versionNameSuffix = "-demo"
            buildConfigField("boolean", "SKIP_VALIDATION", "true")
        }
        create("full") {
            dimension = "version"
            buildConfigField("boolean", "SKIP_VALIDATION", "false")
        }
    }

    buildFeatures{
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.firebase.appdistribution.gradle)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.androidx.fragment.ktx)
    runtimeOnly(libs.kotlinx.coroutines.android)
    runtimeOnly(libs.androidx.lifecycle.runtime.ktx)

    coreLibraryDesugaring (libs.desugar.jdk.libs)
}