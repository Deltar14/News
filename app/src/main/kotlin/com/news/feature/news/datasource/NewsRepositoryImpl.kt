package com.news.feature.news.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.news.feature.news.datasource.paging.NewsPagingSource
import com.news.feature.news.domain.NewsItemsModel
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
	private val paging: PagingSource<Int, NewsItemsModel>,
) : NewsRepository {

	override fun getNews(): Flow<PagingData<NewsItemsModel>> {
		return Pager(
			config = PagingConfig(
				pageSize = NewsPagingSource.PAGE_SIZE,
				enablePlaceholders = false
			),
			pagingSourceFactory = { paging }
		).flow
	}
}