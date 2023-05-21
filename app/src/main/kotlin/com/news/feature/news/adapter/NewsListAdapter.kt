package com.news.feature.news.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.news.feature.news.domain.NewsItemsModel
import com.news.feature.news.adapter.holder.NewsListViewHolder

class NewsListAdapter :
	PagingDataAdapter<NewsItemsModel, NewsListViewHolder>(NewsListDiff()) {

	var onClick: (NewsItemsModel) -> Unit = {}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListViewHolder {
		return NewsListViewHolder.newInstance(parent)
	}

	override fun onBindViewHolder(holder: NewsListViewHolder, position: Int) {
		getItem(position)?.let { newsModel ->
			holder.setup(newsModel) {
				onClick.invoke(it)
			}
		}
	}
}