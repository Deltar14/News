package com.news.feature.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.news.feature.news.domain.NewsItemsModel
import com.news.feature.news.domain.usecase.GetNewsUseCase
import com.news.utils.base.BaseViewModel
import com.news.utils.livedata.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class NewsPageViewModel(
	private val getNewsUseCase: GetNewsUseCase,
) : BaseViewModel() {

	private val _showMessageError = SingleLiveEvent<String?>()
	val showMessageError: LiveData<String?>
		get() = _showMessageError

	fun getNews(keyword: String? = null): Flow<PagingData<NewsItemsModel>> {
		return getNewsUseCase.fetchNews(keyword)
			.cachedIn(viewModelScope)
			.flowOn(Dispatchers.IO)
			.catch { _showMessageError.postValue(it.message) }
	}
}