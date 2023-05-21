package com.news.feature.news.datasource.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsApi {

	@GET("everything?sortBy=publishedAt")
	suspend fun getNews(
		@Header("X-Api-Key") apiKey: String,
		@Query("q") keyword: String,
		@Query("language") language: String,
		@Query("page") page: Int,
		@Query("pageSize") pageSize: Int,
	): Response<NewsItemsResponse>
}