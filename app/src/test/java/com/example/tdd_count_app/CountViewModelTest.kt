package com.example.tdd_count_app

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(AndroidJUnit4::class)
class CountViewModelTest {
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    // The execution order of `TestCoroutineDispatcher` can be confusing, and the mechanism of " +
    //    "pausing is typically misunderstood. Please use `StandardTestDispatcher` or `UnconfinedTestDispatcher` instead.
    private val dispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var mockTotalCountObserver: Observer<Int>

    @Mock
    lateinit var mockTotalUnreadCountObserver: Observer<Int>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.cancel()
    }

    @Test
    fun onClickPlusButton_プラスボタンをクリックした時_渡された値の合計が通知できていること() {
        val countViewModel = CountViewModel(FetchTotalUnreadCountUseCase())
        countViewModel.totalCount.observeForever(mockTotalCountObserver)

        countViewModel.onClickPlusButton(10, 20)

        verify(mockTotalCountObserver).onChanged(30)
    }

    @Test
    fun 未読数の合計を取得して値を渡せていること() = dispatcher.runBlockingTest {
        val fetchTotalUnreadCountUseCase = mock<FetchTotalUnreadCountUseCase>()
        whenever(fetchTotalUnreadCountUseCase.invoke())
            .thenReturn(UnreadCount(1, 2))

        val countViewModel = CountViewModel(fetchTotalUnreadCountUseCase)
        countViewModel.fetchTotalUnreadCount()
        countViewModel.totalUnreadCount.observeForever(mockTotalUnreadCountObserver)
        val actual = countViewModel.totalUnreadCount.value
        assertEquals(3, actual)
    }
}