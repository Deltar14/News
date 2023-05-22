package com.news

import java.util.*

private fun isPalindrome(str: String): String {
	var i = 0
	var j = str.length - 1
	while (i < j) {
		if (str[i] != str[j]) return "$str isn’t a palindrome"
		i++
		j--
	}
	return "$str is a palindrome"
}

fun main(args: Array<String>) {
	val reader = Scanner(System.`in`)
	print("input => ")
	val inputString: String = reader.next()
	println(isPalindrome(inputString))
}
