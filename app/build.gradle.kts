plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
    kotlin("kapt")
    alias(libs.plugins.daggerHiltAndroid)
}

android {
    namespace = "com.blackhand.chatgpt"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.blackhand.chatgpt"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project("::presentation:splash"))
    implementation(project("::presentation:chat"))
    implementation(project("::presentation:register"))
    implementation(project("::data"))
    implementation(project("::domin"))
    implementation(project("::core"))
    implementation(project("::common:sharedui"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Dagger Hilt
    implementation(libs.dagger.hilt)
    kapt(libs.dagger.hilt.compiler)

}