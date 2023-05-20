package com.news.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.news.databinding.FragmentNewsPageBinding
import com.news.utils.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsPageFragment: BaseFragment<FragmentNewsPageBinding>() {

	private val viewModel: NewsPageViewModel by viewModel()

	override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentNewsPageBinding
		get() = FragmentNewsPageBinding::inflate

	override fun setup() {
		observeLoadingState = viewModel
//		initRecycler()
	}

//	override fun observe() {
//		super.observe()
//		lifecycleScope.launchWhenCreated {
//			viewModel.getHistory().collectLatest {
//				adapter.submitData(it)
//			}
//		}
//
//		viewModel.toLicense.observe(viewLifecycleOwner) {
//			navigateToLicense(it)
//		}
//
//		viewModel.showMessageError.observe(viewLifecycleOwner) {
//			showMessageError(it)
//		}
//	}
//
//	override fun initListener() {
//		super.initListener()
//		binding.saleHistoryToolbar.onClickButtonLeft {
//			activity?.finish()
//		}
//
//		adapter.onClick = {
//			viewModel.processHistoryClick(it)
//		}
//	}
//
//	private fun initRecycler() {
//		binding.historyRecycler.adapter = adapter
//	}
//
//	private fun navigateToLicense(saleHistory: SaleHistory) {
//		val directions = SaleHistoryFragmentDirections.toLicenseCustomer(saleHistory)
//		findNavController().navigate(directions)
//	}
//
//	private fun showMessageError(message: String?) {
//		context?.let {
//			CommonAlertDialog(it)
//				.setTitle(R.string.common_sorry)
//				.setMessage(message ?: getString(R.string.common_message_error))
//				.show()
//		}
//	}
}