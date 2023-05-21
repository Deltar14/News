package com.news.feature.news.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.news.feature.news.datasource.network.NewsApi
import com.news.feature.news.datasource.paging.NewsPagingSource
import com.news.feature.news.domain.NewsItemsModel
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
	private val api: NewsApi,
) : NewsRepository {

	override fun getNews(keyword: String?): Flow<PagingData<NewsItemsModel>> {
		return Pager(
			config = PagingConfig(
				pageSize = NewsPagingSource.PAGE_SIZE,
				enablePlaceholders = false
			),
			pagingSourceFactory = { NewsPagingSource(api, keyword) }
		).flow
	}
}