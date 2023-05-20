package com.news.utils.base

import android.content.res.AssetManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<Binding: ViewBinding>: AppCompatActivity() {

	private var _binding: ViewBinding? = null
	val binding: Binding
		get() = _binding as Binding

	abstract val bindingInflater: (LayoutInflater) -> Binding
	abstract fun setup()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		_binding = bindingInflater.invoke(layoutInflater)
		if (_binding is ViewDataBinding) {
			(_binding as ViewDataBinding).lifecycleOwner = this
		}
		setContentView(binding.root)
		initData()
		setup()
		initListener()
	}

	override fun getAssets(): AssetManager = resources.assets

	protected open fun initData() {}

	protected open fun initListener() {}

	override fun onDestroy() {
		super.onDestroy()
		_binding = null
	}
}