plugins {
	id("com.android.application")
	id("kotlin-android")
	id("kotlin-parcelize")
	id("kotlin-kapt")
}

android {
	namespace = "com.news"
	compileSdk = 33
	defaultConfig {
		applicationId = "com.news"
		minSdk = 21
		targetSdk = 33
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

		val apiKey = project.property("API_KEY").toString()
		buildConfigField("String", "API_KEY", apiKey)
	}

	buildTypes {
		debug {
			isDebuggable = true
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
		release {
			isDebuggable = false
			isMinifyEnabled = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	buildFeatures {
		viewBinding = true
		dataBinding = true
	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_11
		targetCompatibility = JavaVersion.VERSION_11
	}

	kotlinOptions {
		jvmTarget = JavaVersion.VERSION_11.toString()
	}
}

dependencies {

	implementation("androidx.core:core-ktx:1.10.1")
	implementation("androidx.appcompat:appcompat:1.6.1")
	implementation("com.google.android.material:material:1.9.0")
	testImplementation("junit:junit:4.13.2")
	testImplementation("androidx.arch.core:core-testing:2.2.0")
	testImplementation("io.mockk:mockk:1.12.3")

	androidTestImplementation("androidx.test.ext:junit:1.1.5")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

	//Retrofit & logging
	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("com.squareup.retrofit2:converter-gson:2.9.0")
	//noinspection GradleDependency
	implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

	//Chucker
	debugImplementation("com.github.chuckerteam.chucker:library:3.5.2")
	releaseImplementation("com.github.chuckerteam.chucker:library-no-op:3.5.2")

	//coroutines
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
	testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

	//koin
	implementation("io.insert-koin:koin-android:3.3.3")
	implementation("io.insert-koin:koin-android-compat:3.3.3")
	implementation("io.insert-koin:koin-androidx-workmanager:3.3.3")
	implementation("io.insert-koin:koin-androidx-navigation:3.3.3")

	//Coil for load image
	implementation("io.coil-kt:coil:2.2.2")

	//Lottie
	implementation("com.airbnb.android:lottie:4.2.2")
}