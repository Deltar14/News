package com.news.utils.dataholder

import com.google.gson.annotations.SerializedName

data class ResponseModel<T>(
	@SerializedName("status")
	val status: String? = "",
	@SerializedName("code")
	val code: String? = "",
	@SerializedName("message")
	val message: String = "",
	@SerializedName("data")
	val data: T? = null
)