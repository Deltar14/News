package com.news.utils.base

import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class BaseViewModel: ViewModel(), ObserveLoadingState {

	private val _loadingState = MutableLiveData<Boolean>()
	override val loadingState: LiveData<Boolean>
		get() = _loadingState

	fun showLoading() {
		_loadingState.postValue(true)
	}

	fun hideLoading() {
		_loadingState.postValue(false)
	}
}