package com.news.feature.news.domain.usecase

import androidx.paging.PagingData
import com.news.feature.news.datasource.NewsRepository
import com.news.feature.news.domain.NewsItemsModel
import kotlinx.coroutines.flow.Flow

class GetNewsUseCase(private val repository: NewsRepository) {

	fun fetchNews(keyword: String?): Flow<PagingData<NewsItemsModel>> {
		return repository.getNews(keyword)
	}
}