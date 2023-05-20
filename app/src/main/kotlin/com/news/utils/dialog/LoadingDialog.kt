package com.news.utils.dialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.news.databinding.DialogLoadingBinding

class LoadingDialog(context: Context) {

	private val binding: DialogLoadingBinding by lazy {
		DialogLoadingBinding.inflate(LayoutInflater.from(context), null, false)
	}

	private val alert: AlertDialog by lazy { AlertDialog.Builder(context).create() }

	init {
		setup()
	}

	private fun setup() {
		alert.setView(binding.root)
		alert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
		alert.setCancelable(false)
	}

	fun showLoading() {
		alert.show()
	}

	fun hideLoading() {
		alert.dismiss()
	}

	companion object {
		fun newInstance(context: Context) = LoadingDialog(context)
	}
}