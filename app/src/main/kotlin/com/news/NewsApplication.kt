package com.news

import android.app.Application
import com.news.di.networkModule
import com.news.di.repositoryModule
import com.news.di.useCaseModule
import com.news.di.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@ExperimentalCoroutinesApi
class NewsApplication : Application() {

	override fun onCreate() {
		super.onCreate()
		initKoin()
	}

	private fun initKoin() {
		startKoin {
			androidLogger()
			androidContext(this@NewsApplication)
			modules(networkModule, repositoryModule, useCaseModule, viewModelModule)
		}
	}
}