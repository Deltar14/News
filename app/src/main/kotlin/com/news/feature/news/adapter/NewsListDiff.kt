package com.news.feature.news.adapter

import androidx.recyclerview.widget.DiffUtil
import com.news.feature.news.domain.NewsItemsModel

class NewsListDiff : DiffUtil.ItemCallback<NewsItemsModel>() {

	override fun areItemsTheSame(oldItem: NewsItemsModel, newItem: NewsItemsModel): Boolean {
		return oldItem == newItem
	}

	override fun areContentsTheSame(oldItem: NewsItemsModel, newItem: NewsItemsModel): Boolean {
		return oldItem == newItem
	}
}