package com.news.extensions

import android.app.Activity
import android.os.SystemClock
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View?.setOnClickWithDelay(delayTime: Long = 1000L, onClick: () -> Unit) {
	this?.setOnClickListener(object : View.OnClickListener {
		var lastClickTime = 0L
		override fun onClick(p0: View?) {
			if (SystemClock.elapsedRealtime() - lastClickTime < delayTime) {
				return
			}
			lastClickTime = SystemClock.elapsedRealtime()
			onClick.invoke()
		}
	})
}

fun View?.hideKeyboard() {
	this?.let { view ->
		val imm = view.context.getSystemService(
			Activity.INPUT_METHOD_SERVICE
		) as? InputMethodManager
		imm?.hideSoftInputFromWindow(view.windowToken, 0)
	}
}