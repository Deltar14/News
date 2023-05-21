package com.news.feature.news.datasource.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.news.feature.news.datasource.network.NewsApi
import com.news.BuildConfig
import com.news.feature.news.datasource.network.NewsItemsResponse
import com.news.feature.news.datasource.network.NewsItemsResponse.Companion.toUiModel
import com.news.feature.news.domain.NewsItemsModel
import com.news.utils.exceptions.NetworkException
import retrofit2.Response
import kotlin.math.ceil

class NewsPagingSource(private val api: NewsApi) : PagingSource<Int, NewsItemsModel>() {

	override fun getRefreshKey(state: PagingState<Int, NewsItemsModel>): Int? {
		return state.anchorPosition?.let { anchorPosition ->
			val anchorPage = state.closestPageToPosition(anchorPosition)
			anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
		}
	}

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NewsItemsModel> {
		return try {
			val page = params.key ?: INITIAL_PAGE
			val apiKey = BuildConfig.API_KEY
			val response = api.getNews(
				apiKey = apiKey,
				keyword = "everything",
				language = "en",
				page = page, pageSize = PAGE_SIZE
			)
			val body = processResponse(response)
			val listData = body.toUiModel()
			val maxPage = ceil((body.totalResults ?: 0).toDouble() / PAGE_SIZE).toInt()
			val nextKey =
				if (listData.isEmpty() or (page + 1 == maxPage)) null else page.plus(1)
			val preKey = if (page == INITIAL_PAGE) null else page.minus(1)
			LoadResult.Page(
				data = listData,
				nextKey = nextKey,
				prevKey = preKey
			)
		} catch (exception: Exception) {
			LoadResult.Error(exception)
		}
	}

	private fun processResponse(response: Response<NewsItemsResponse>): NewsItemsResponse {
		return if (response.isSuccessful) {
			response.body() ?: throw NetworkException()
		} else throw NetworkException()
	}

	companion object {
		const val INITIAL_PAGE = 1
		const val PAGE_SIZE = 10
	}
}