package com.news.feature

import android.view.LayoutInflater
import com.news.databinding.ActivityNewsHomeBinding
import com.news.utils.base.BaseActivity

class NewsHomeActivity: BaseActivity<ActivityNewsHomeBinding>() {

	override val bindingInflater: (LayoutInflater) -> ActivityNewsHomeBinding
		get() = ActivityNewsHomeBinding::inflate

	override fun setup() {}

}