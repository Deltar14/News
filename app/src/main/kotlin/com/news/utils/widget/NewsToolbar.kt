package com.news.utils.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.news.R
import com.news.databinding.ViewNewsToolbarBinding
import com.news.extensions.setOnClickWithDelay

class NewsToolbar @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet?,
	defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

	private val binding: ViewNewsToolbarBinding by lazy {
		ViewNewsToolbarBinding.inflate(LayoutInflater.from(context), this, true)
	}

	var title: String = ""
		set(value) {
			field = value
			binding.titleToolbarView.text = value
		}

	var isShowButtonLeft: Boolean = true
		set(value) {
			field = value
			binding.imageButtonBackToolbarView.isVisible = value
		}

	init {
		setAttrs(attrs)
	}

	private fun setAttrs(attrs: AttributeSet?) {
		attrs?.let { attrsSet ->
			val typedArray = context.obtainStyledAttributes(attrsSet, R.styleable.NewsToolbar)
			typedArray.getString(R.styleable.NewsToolbar_title)?.let { title = it }
			typedArray.getBoolean(R.styleable.NewsToolbar_showButtonLeft, true)
				.let { isShowButtonLeft = it }
			typedArray.recycle()
		}
	}

	fun onClickButtonLeft(function: () -> Unit) {
		binding.imageButtonBackToolbarView.setOnClickWithDelay { function.invoke() }
	}
}