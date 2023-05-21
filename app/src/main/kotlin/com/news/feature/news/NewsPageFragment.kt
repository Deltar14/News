package com.news.feature.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.news.R
import com.news.databinding.FragmentNewsPageBinding
import com.news.extensions.hideKeyboard
import com.news.feature.news.adapter.NewsListAdapter
import com.news.feature.news.domain.NewsItemsModel
import com.news.utils.base.BaseFragment
import com.news.utils.dialog.CommonAlertDialog
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.concurrent.schedule

class NewsPageFragment : BaseFragment<FragmentNewsPageBinding>() {

	private val viewModel: NewsPageViewModel by viewModel()
	private val adapter: NewsListAdapter by lazy { NewsListAdapter() }

	override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentNewsPageBinding
		get() = FragmentNewsPageBinding::inflate

	override fun setup() {
		observeLoadingState = viewModel
		initToolbar()
		initRecycler()
	}

	override fun observe() {
		super.observe()
		lifecycleScope.launchWhenCreated {
			viewModel.getNews().collectLatest {
				adapter.submitData(it)
			}
		}
		viewModel.showMessageError.observe(this) {
			showMessageError(it)
		}
	}

	override fun initListener() {
		super.initListener()
		adapter.onClick = {
			navigateToArticleDetail(it)
		}
		binding.searchView.apply {
			setOnQueryTextListener(object : SearchView.OnQueryTextListener {
				override fun onQueryTextSubmit(query: String?): Boolean {
					binding.searchView.clearFocus()
					binding.searchView.hideKeyboard()
					if (query != null) {
						lifecycleScope.launchWhenResumed {
							viewModel.getNews(query).collectLatest {
								adapter.submitData(it)
							}
						}
					}
					return true
				}

				override fun onQueryTextChange(query: String?): Boolean {
					if (!query.isNullOrBlank() && query.length >= MINIMUM_CHARACTER_LENGTH_SEARCH) {
						Timer().schedule(delay = DEFAULT_DELAY_SEARCH) {
							lifecycleScope.launchWhenResumed {
								viewModel.getNews(query).collectLatest {
									adapter.submitData(it)
								}
							}
						}
					}
					return false
				}
			})
		}
	}

	private fun initToolbar() = with(binding) {
		newsPageToolbar.apply {
			title = getString(R.string.title_news_page)
			isShowButtonLeft = false
		}
	}

	private fun initRecycler() = with(binding) {
		newsRecyclerView.adapter = adapter
	}

	private fun navigateToArticleDetail(newsItemsModel: NewsItemsModel) {
//		val bundle = bundleOf("newsItemsModel" to newsItemsModel)

		val directions = NewsPageFragmentDirections.toNewsDetails(newsItemsModel)
		findNavController().navigate(directions)
	}

	private fun showMessageError(message: String?) {
		context?.let {
			CommonAlertDialog(it)
				.setOnCloseClick {
					lifecycleScope.launchWhenResumed {
						viewModel.getNews().collectLatest { newsItemsModel ->
							adapter.submitData(newsItemsModel)
						}
					}
				}
				.setTitle(R.string.common_sorry)
				.setMessage(message ?: getString(R.string.common_message_error))
				.show()
		}
	}

	companion object {
		private const val DEFAULT_DELAY_SEARCH = 2000L
		private const val MINIMUM_CHARACTER_LENGTH_SEARCH = 3
	}
}
