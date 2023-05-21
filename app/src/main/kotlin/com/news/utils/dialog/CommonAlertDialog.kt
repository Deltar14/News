package com.news.utils.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.news.databinding.DialogCommonAlertBinding
import com.news.extensions.setOnClickWithDelay

class CommonAlertDialog(private val context: Context) {

	private val dialog: AlertDialog by lazy { AlertDialog.Builder(context).create() }

	private val binding: DialogCommonAlertBinding by lazy {
		DialogCommonAlertBinding.inflate(LayoutInflater.from(context), null, false)
	}

	private var onCloseClickListener: () -> Unit = {}

	init {
		dialog.setView(binding.root)
		initListener()
	}

	fun setTitle(@StringRes stringRes: Int): CommonAlertDialog {
		return setTitle(context.getString(stringRes))
	}

	fun setTitle(title: String): CommonAlertDialog {
		binding.titleTextView.text = title
		return this
	}

	fun setMessage(@StringRes stringRes: Int): CommonAlertDialog {
		return setMessage(context.getString(stringRes))
	}

	fun setMessage(message: String): CommonAlertDialog {
		binding.message.text = message
		return this
	}

	fun setOnCloseClick(onCloseClick: () -> Unit): CommonAlertDialog {
		onCloseClickListener = onCloseClick
		return this
	}

	fun show() {
		dialog.show()
	}

	private fun initListener() {
		binding.closeButton.setOnClickWithDelay {
			onCloseClickListener.invoke()
			dialog.dismiss()
		}
	}
}