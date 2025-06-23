import java.util.Properties
import java.io.FileInputStream

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
}

val apikeyPropertiesFile = rootProject.file("apikey.properties")
val apikeyProperties = Properties()
apikeyProperties.load(FileInputStream(apikeyPropertiesFile))

android {
    namespace = "com.example.foodyapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.foodyapplication"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    flavorDimensions += "default"
    productFlavors {
        create("dev") {
            applicationIdSuffix = ".dev"
            buildConfigField(
                "String",
                "BASE_MAPBOX_ACCESS_TOKEN",
                "\"${apikeyProperties["BASE_MAPBOX_ACCESS_TOKEN_DEV"]}\""
            )
            buildConfigField(
                "String",
                "BASE_URL_JSON_PLACE_HOLDER",
                "\"${apikeyProperties["BASE_URL_JSON_PLACE_HOLDER_DEV"]}\""
            )
            buildConfigField(
                "String",
                "BASE_URL_STACK_OVER_FLOW",
                "\"${apikeyProperties["BASE_URL_STACK_OVER_FLOW_DEV"]}\""
            )
        }
        create("staging") {
            applicationIdSuffix = ".staging"
            buildConfigField(
                "String",
                "BASE_MAPBOX_ACCESS_TOKEN",
                "\"${apikeyProperties["BASE_MAPBOX_ACCESS_TOKEN_STAGING"]}\""
            )
            buildConfigField(
                "String",
                "BASE_URL_JSON_PLACE_HOLDER",
                "\"${apikeyProperties["BASE_URL_JSON_PLACE_HOLDER_STAGING"]}\""
            )
            buildConfigField(
                "String",
                "BASE_URL_STACK_OVER_FLOW",
                "\"${apikeyProperties["BASE_URL_STACK_OVER_FLOW_STAGING"]}\""
            )
        }
        create("live") {
            applicationIdSuffix = ".live"
            buildConfigField(
                "String",
                "BASE_MAPBOX_ACCESS_TOKEN",
                "\"${apikeyProperties["BASE_MAPBOX_ACCESS_TOKEN_LIVE"]}\""
            )
            buildConfigField(
                "String",
                "BASE_URL_JSON_PLACE_HOLDER",
                "\"${apikeyProperties["BASE_URL_JSON_PLACE_HOLDER_LIVE"]}\""
            )
            buildConfigField(
                "String",
                "BASE_URL_STACK_OVER_FLOW",
                "\"${apikeyProperties["BASE_URL_STACK_OVER_FLOW_LIVE"]}\""
            )
        }
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

    buildFeatures {
        dataBinding = true
        viewBinding = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

val hilt_version = "2.50"
val retrofit_version = "2.9.0"
val moshi_version = "1.11.0"
val ok_httpclient_version = "4.9.0"
val coroutines_version = "1.5.2"
val glide_version = "4.12.0"
val room_version = "2.4.2"
val constraintlayout_version = "2.1.3"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.0")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:$constraintlayout_version")

    implementation("androidx.navigation:navigation-fragment-ktx:2.4.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.4.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    // Retrofit + Moshi
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-moshi:$retrofit_version")
    implementation("com.squareup.moshi:moshi-kotlin:$moshi_version")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:$ok_httpclient_version")
    implementation("com.squareup.okhttp3:logging-interceptor:$ok_httpclient_version")

    // Glide
    implementation("com.github.bumptech.glide:glide:$glide_version")

    // Hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")

    // Room
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    testImplementation("androidx.room:room-testing:$room_version")

    // ViewPager2, Activity KTX, CircleImageView
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("androidx.activity:activity-ktx:1.4.0")
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // Unit Test
    testImplementation("junit:junit:4.13.2")
    testImplementation("com.google.truth:truth:1.1.3")
    testImplementation("org.mockito:mockito-core:3.12.4")
    testImplementation("org.mockito:mockito-inline:2.25.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_version")
    testImplementation("androidx.test.ext:junit-ktx:1.1.3")
    testImplementation("org.robolectric:robolectric:4.5.1")
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("androidx.test:core:1.4.0")

    // Instrumentation Test
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("junit:junit:4.13.2")
    androidTestImplementation("com.google.truth:truth:1.1.3")
    androidTestImplementation("org.mockito:mockito-core:3.12.4")
    androidTestImplementation("org.mockito:mockito-android:3.12.4")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_version")
    androidTestImplementation("androidx.test.ext:junit-ktx:1.1.3")
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
    androidTestImplementation("com.squareup.okhttp3:mockwebserver:$ok_httpclient_version")
    androidTestImplementation("com.google.dagger:hilt-android-testing:$hilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")

    //Circle Indicator
    implementation("me.relex:circleindicator:1.3.2")
    implementation("me.relex:circleindicator:2.1.6")

    //Maps
    implementation("org.osmdroid:osmdroid-android:6.1.10")
}