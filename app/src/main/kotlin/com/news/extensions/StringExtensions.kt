package com.news.extensions

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.abs

const val DATE_TIME_OF_PATTERN = "yyyy-MM-d'T'HH:mm:ss'Z'"
const val DATE_TIME_PATTERN_FORMAT = "MMM dd, hh:mm a"

fun String.toDateTimeFormat(): String {
	return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
		val inputFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_OF_PATTERN)
		val outputFormatter: DateTimeFormatter =
			DateTimeFormatter.ofPattern(DATE_TIME_PATTERN_FORMAT)
		val date: LocalDateTime = LocalDateTime.parse(this, inputFormatter)
		outputFormatter.format(date)
	} else {
		val parser = SimpleDateFormat(DATE_TIME_OF_PATTERN, Locale.getDefault())
		val formatter = SimpleDateFormat(DATE_TIME_PATTERN_FORMAT, Locale.getDefault())
		return parser.parse(this)?.let { formatter.format(it) }.orEmpty()
	}
}