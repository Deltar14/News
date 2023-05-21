package com.news.feature.news.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.news.R
import com.news.databinding.ItemNewsBinding
import com.news.extensions.setOnClickWithDelay
import com.news.extensions.toDateTimeFormat
import com.news.feature.news.domain.NewsItemsModel

class NewsListViewHolder(private val binding: ItemNewsBinding) :
	RecyclerView.ViewHolder(binding.root) {

	fun setup(newsItemsModel: NewsItemsModel, onClick: (NewsItemsModel) -> Unit) {
		binding.apply {
			newsImageView.load(newsItemsModel.urlToImage) {
				crossfade(true)
				error(R.drawable.image_placeholder)
				placeholder(R.drawable.image_placeholder)
			}
			titleTextView.text = newsItemsModel.title
			descriptionTextView.text = newsItemsModel.description
			publishedAtTextView.text = newsItemsModel.publishedAt.toDateTimeFormat()
			iconChevronImageView.setOnClickWithDelay {
				onClick.invoke(newsItemsModel)
			}
		}
	}

	companion object {
		fun newInstance(parent: ViewGroup): NewsListViewHolder {
			val inflater = LayoutInflater.from(parent.context)
			val binding = ItemNewsBinding.inflate(inflater, parent, false)
			return NewsListViewHolder(binding)
		}
	}
}