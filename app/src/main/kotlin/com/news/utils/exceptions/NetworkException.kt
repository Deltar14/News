package com.news.utils.exceptions

class NetworkException(val mes: String? = null) : Throwable(message = mes)