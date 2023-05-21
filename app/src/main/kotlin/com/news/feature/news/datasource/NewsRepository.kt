package com.news.feature.news.datasource

import androidx.paging.PagingData
import com.news.feature.news.domain.NewsItemsModel
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
	fun getNews(): Flow<PagingData<NewsItemsModel>>
}