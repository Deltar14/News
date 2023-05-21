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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

class NewsPageViewModel(
	private val getNewsUseCase: GetNewsUseCase
) : BaseViewModel() {

	private val _showMessageError = SingleLiveEvent<String?>()
	val showMessageError: LiveData<String?>
		get() = _showMessageError

	fun getNews(): Flow<PagingData<NewsItemsModel>> {
		return getNewsUseCase.fetchNews()
			.cachedIn(viewModelScope)
			.flowOn(Dispatchers.IO)
			.catch { _showMessageError.postValue(it.message) }
	}

}