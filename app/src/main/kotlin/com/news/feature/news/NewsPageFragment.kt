package com.news.feature.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.news.R
import com.news.databinding.FragmentNewsPageBinding
import com.news.feature.news.adapter.NewsListAdapter
import com.news.feature.news.domain.NewsItemsModel
import com.news.utils.base.BaseFragment
import com.news.utils.dialog.CommonAlertDialog
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsPageFragment: BaseFragment<FragmentNewsPageBinding>() {

	private val viewModel: NewsPageViewModel by viewModel()
	private val adapter: NewsListAdapter by lazy { NewsListAdapter() }

	override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentNewsPageBinding
		get() = FragmentNewsPageBinding::inflate

	override fun setup() {
		observeLoadingState = viewModel
		initRecycler()
	}

	override fun observe() {
		super.observe()
		lifecycleScope.launchWhenCreated {
			viewModel.getNews().collectLatest {
				adapter.submitData(it)
			}
		}

		viewModel.showMessageError.observe(viewLifecycleOwner) {
//			showMessageError(it)
		}
	}

	override fun initListener() {
		super.initListener()
		adapter.onClick = {
			navigateToArticleDetail(it)
		}
	}

	private fun initRecycler() = with(binding) {
		newsRecyclerView.adapter = adapter
	}

	private fun navigateToArticleDetail(newsItemsModel: NewsItemsModel) {
//		val directions = MewsPageFragmentDirections.toArticleDetail(newsItemsModel)
//		findNavController().navigate(directions)
	}

	private fun showMessageError(message: String?) {
		context?.let {
			CommonAlertDialog(it)
				.setOnCloseClick {
					//Handle call api again
				}
				.setTitle(R.string.common_sorry)
				.setMessage(message ?: getString(R.string.common_message_error))
				.show()
		}
	}
}