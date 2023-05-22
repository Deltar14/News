package com.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
open class BaseUnitTest {
	@get:Rule
	val rule = InstantTaskExecutorRule()

	@Before
	open fun setupTest() {
		MockKAnnotations.init(this)
	}

	@After
	open fun tearDown() {
		Dispatchers.resetMain()
	}
}