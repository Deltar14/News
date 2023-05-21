package com.news.feature.news.datasource.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.news.BuildConfig
import com.news.feature.news.datasource.network.NewsApi
import com.news.feature.news.datasource.network.NewsItemsResponse
import com.news.feature.news.datasource.network.NewsItemsResponse.Companion.toUiModel
import com.news.feature.news.domain.NewsItemsModel
import com.news.utils.dataholder.ResponseModel
import com.news.utils.dataholder.exceptions.NetworkException
import retrofit2.Response
import kotlin.math.ceil

class NewsPagingSource(
	private val api: NewsApi,
	private val keyword: String?
) :
	PagingSource<Int, NewsItemsModel>() {

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
				keyword = getKeyword(),
				language = "en",
				page = page, pageSize = PAGE_SIZE
			)
			val body = processResponse(response)
			val listData = body.data?.toUiModel()
			val maxPage = ceil((body.data?.totalResults ?: 0).toDouble() / PAGE_SIZE).toInt()
			val nextKey =
				if (listData.isNullOrEmpty() or (page + 1 == maxPage)) null else page.plus(1)
			val preKey = if (page == INITIAL_PAGE) null else page.minus(1)
			LoadResult.Page(
				data = listData ?: emptyList(),
				nextKey = nextKey,
				prevKey = preKey
			)
		} catch (exception: NetworkException) {
			LoadResult.Error(exception)
		}
	}

	private fun getKeyword(): String {
		return if (keyword.isNullOrBlank()) {
			DEFAULT_KEY_WORD
		} else {
			keyword
		}
	}

	private fun processResponse(response: Response<ResponseModel<NewsItemsResponse>>): ResponseModel<NewsItemsResponse> {
		return if (response.isSuccessful) {
			response.body()?.apply {
				if (data == null) throw NetworkException(mes = message)
			} ?: throw NetworkException()
		} else throw NetworkException()
	}

	companion object {
		const val INITIAL_PAGE = 1
		const val PAGE_SIZE = 10
		private const val DEFAULT_KEY_WORD = "everything"
	}
}