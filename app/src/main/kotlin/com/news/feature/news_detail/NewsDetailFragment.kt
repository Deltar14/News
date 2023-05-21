package com.news.feature.news_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.load
import com.news.R
import com.news.databinding.FragmentNewsDetailsBinding
import com.news.extensions.toDateTimeFormat
import com.news.feature.news.domain.NewsItemsModel
import com.news.utils.base.BaseFragment

class NewsDetailFragment : BaseFragment<FragmentNewsDetailsBinding>() {

	override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentNewsDetailsBinding
		get() = FragmentNewsDetailsBinding::inflate

	override fun setup() {
		binding.newsDetailsToolbar.apply {
			title = getString(R.string.title_news_detail_page)
			isShowButtonLeft = true
		}
		arguments?.let {
			bindView(NewsDetailFragmentArgs.fromBundle(it).newsItemModel)
		}
	}

	override fun initListener() {
		super.initListener()
		binding.newsDetailsToolbar.onClickButtonLeft {
			findNavController().popBackStack()
		}
	}

	private fun bindView(newsItemModel: NewsItemsModel) = with(binding) {
		newsImageView.load(newsItemModel.urlToImage) {
			crossfade(true)
			error(R.drawable.image_placeholder)
			placeholder(R.drawable.image_placeholder)
		}
		titleTextView.text = newsItemModel.title
		descriptionTextView.text = newsItemModel.content
		publishedAtTextView.text = newsItemModel.publishedAt.toDateTimeFormat()
	}
}