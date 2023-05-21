package com.news.feature.news.domain


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsItemsModel(
	var source: SourceModel = SourceModel(),
	var author: String = "",
	var title: String = "",
	var description: String = "",
	var url: String = "",
	var urlToImage: String = "",
	var publishedAt: String = "",
	var content: String = "",
) : Parcelable