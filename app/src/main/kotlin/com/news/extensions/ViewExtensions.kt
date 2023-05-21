package com.news.extensions

import android.os.SystemClock
import android.view.View

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