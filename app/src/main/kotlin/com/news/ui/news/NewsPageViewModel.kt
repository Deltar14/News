package com.news.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.news.utils.base.BaseViewModel
import com.news.utils.livedata.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

class NewsPageViewModel() : BaseViewModel() {

//    private val _toLicense = SingleLiveEvent<SaleHistory>()
//    val toLicense: LiveData<SaleHistory>
//        get() = _toLicense
//
//    private val _showMessageError = SingleLiveEvent<String?>()
//    val showMessageError: LiveData<String?>
//        get() = _showMessageError
//
//    fun getHistory(): Flow<PagingData<Transaction>> {
//        return getSaleHistoryTransactionsUseCase.fetchSaleHistoryTransactions()
//            .cachedIn(viewModelScope)
//            .flowOn(Dispatchers.IO)
//            .catch { _showMessageError.postValue(it.message) }
//    }
//
//    fun processHistoryClick(transaction: Transaction) {
//        if (transaction.verification == SaleHistoryStatus.PAID) {
//            _toLicense.postValue(transaction.toSaleHistory())
//        }
//    }
}