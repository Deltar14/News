package com.news.feature.news.datasource.network


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.news.feature.news.domain.NewsItemsModel
import com.news.feature.news.domain.SourceModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsItemsResponse(
	@SerializedName("status")
	val status: String?,
	@SerializedName("totalResults")
	var totalResults: Int?,
	@SerializedName("articles")
	val articles: List<NewsItems>?,
) : Parcelable {
	companion object {
		fun NewsItemsResponse.toUiModel(): List<NewsItemsModel> {
			val newsModelList: MutableList<NewsItemsModel> = mutableListOf()
			this.articles?.forEach {
				val newsModel = NewsItemsModel()
				newsModel.source = mapSourceModel(it.source)
				newsModel.author = it.author.orEmpty()
				newsModel.title = it.title.orEmpty()
				newsModel.description = it.description.orEmpty()
				newsModel.url = it.url.orEmpty()
				newsModel.urlToImage = it.urlToImage.orEmpty()
				newsModel.publishedAt = it.publishedAt.orEmpty()
				newsModel.content = it.content.orEmpty()
				newsModelList.add(newsModel)
			}
			return newsModelList.toList()
		}

		private fun mapSourceModel(source: SourceModel?): SourceModel {
			return SourceModel(
				id = source?.id.orEmpty(),
				name = source?.name.orEmpty()
			)
		}
	}
}

@Parcelize
data class NewsItems(
	@SerializedName("source")
	var source: SourceModel?,
	@SerializedName("author")
	var author: String?,
	@SerializedName("title")
	var title: String?,
	@SerializedName("description")
	var description: String?,
	@SerializedName("url")
	var url: String?,
	@SerializedName("urlToImage")
	var urlToImage: String?,
	@SerializedName("publishedAt")
	var publishedAt: String?,
	@SerializedName("content")
	var content: String?,
) : Parcelable

@Parcelize
data class Source(
	@SerializedName("id")
	var id: String?,
	@SerializedName("name")
	var name: String?,
) : Parcelable
