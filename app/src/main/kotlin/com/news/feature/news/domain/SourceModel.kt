package com.news.feature.news.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourceModel(
	var id: String = "",
	var name: String = "",
) : Parcelable
