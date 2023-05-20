package com.news.utils.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.news.utils.dialog.LoadingDialog

abstract class BaseFragment<Binding: ViewBinding>: Fragment()  {

	private var _binding: ViewBinding? = null
	val binding: Binding
		get() = _binding as Binding

	private val loadingDialog: LoadingDialog? by lazy { context?.let { LoadingDialog.newInstance(it) } }

	var observeLoadingState: ObserveLoadingState? = null

	abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding
	abstract fun setup()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		initData()
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_binding = bindingInflater.invoke(inflater, container, false)
		if (_binding is ViewDataBinding) {
			(_binding as ViewDataBinding).lifecycleOwner = this
		}
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setup()
		observe()
		initListener()
	}

	protected open fun initData() {}

	protected open fun observe() {
		observeLoadingState?.loadingState?.observe(viewLifecycleOwner) { isLoading ->
			if (isLoading) {
				loadingDialog?.showLoading()
			}
			else {
				loadingDialog?.hideLoading()
			}
		}
	}

	protected open fun initListener() {}


	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}
