package com.news.di


import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.dsl.module
import com.news.feature.news.domain.usecase.GetNewsUseCase

@ExperimentalCoroutinesApi
val useCaseModule = module {
	factory { GetNewsUseCase(get()) }
}