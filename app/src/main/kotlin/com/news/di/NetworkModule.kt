package com.news.di

import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
	single<ChuckerCollector> {
		ChuckerCollector(
			context = androidContext(),
			showNotification = true,
			retentionPeriod = RetentionManager.Period.ONE_HOUR
		)
	}

	single<ChuckerInterceptor> {
		ChuckerInterceptor.Builder(androidContext())
			.collector(get())
			.maxContentLength(NetworkConstants.CHUCKER_MAX_CONTENT)
			.redactHeaders(emptySet())
			.alwaysReadResponseBody(true)
			.build()
	}

	single<OkHttpClient> {
		OkHttpClient.Builder()
			.readTimeout(NetworkConstants.TIME_OUT, TimeUnit.SECONDS)
			.connectTimeout(NetworkConstants.TIME_OUT, TimeUnit.SECONDS)
			.addInterceptor(get<ChuckerInterceptor>())
			.addInterceptor(HttpLoggingInterceptor().apply {
				setLevel(HttpLoggingInterceptor.Level.BODY)
			})
			.build()
	}

	single<Retrofit> {
		Retrofit.Builder()
			.baseUrl(NetworkConstants.BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.client(get())
			.build()
	}
}

object NetworkConstants {
	const val BASE_URL = "https://newsapi.org/v2/"
	const val CHUCKER_MAX_CONTENT = 25000L
	const val TIME_OUT = 30L
}