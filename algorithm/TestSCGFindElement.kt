package com.news

private fun findElement(arr: IntArray, n: Int): String {
	var result = "index not found"
	var leftSum = 0
	var rightSum = 0
	for (i in 0 until n) {
		rightSum += arr[i]
	}
	for (i in 0 until n) {
		rightSum -= arr[i]
		println(rightSum)
		if (leftSum == rightSum) result = "middle index is $i"
		leftSum += arr[i]
		println(leftSum)
	}

	return result
}

fun main(args: Array<String>) {
	val arr = intArrayOf(3, 6, 8, 1, 5, 10, 1, 7)
//		val arr = intArrayOf(1, 3, 5, 7, 9)
//		val arr = intArrayOf(3, 5, 6)
	val n = arr.size
	println(findElement(arr, n))
}
