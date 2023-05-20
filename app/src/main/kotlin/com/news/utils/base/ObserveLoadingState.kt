package com.news.utils.base

import androidx.lifecycle.LiveData

interface ObserveLoadingState {
	val loadingState: LiveData<Boolean>
}