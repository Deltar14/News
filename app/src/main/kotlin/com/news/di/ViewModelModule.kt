package com.news.di


import com.news.feature.news.NewsPageViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

@ExperimentalCoroutinesApi
val viewModelModule = module {
	factory { NewsPageViewModel(get()) }
}