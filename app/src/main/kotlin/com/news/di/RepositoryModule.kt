package com.news.di

import androidx.paging.PagingSource
import com.news.feature.news.datasource.NewsRepository
import com.news.feature.news.datasource.NewsRepositoryImpl
import com.news.feature.news.datasource.network.NewsApi
import com.news.feature.news.datasource.paging.NewsPagingSource
import com.news.feature.news.domain.NewsItemsModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit

@ExperimentalCoroutinesApi
val repositoryModule = module {
	factory<NewsApi> { get<Retrofit>().create(NewsApi::class.java) }
	factoryOf(::NewsRepositoryImpl) { bind<NewsRepository>() }

	//News
	factory<NewsApi> { get<Retrofit>().create(NewsApi::class.java) }
	factory<PagingSource<Int, NewsItemsModel>> { NewsPagingSource(get()) }
	factory<NewsRepository> { NewsRepositoryImpl(get()) }
}