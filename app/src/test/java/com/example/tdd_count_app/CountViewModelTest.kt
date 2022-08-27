package com.example.tdd_count_app

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
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

    private lateinit var countViewModel: CountViewModel

    @Mock
    lateinit var mockTotalCountObserver: Observer<Int>

    @Before
    fun setUp() {
        countViewModel = CountViewModel()
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun onClickPlusButton_プラスボタンをクリックした時_渡された値の合計が通知できていること() {
        countViewModel.totalCount.observeForever(mockTotalCountObserver)

        countViewModel.onClickPlusButton(10, 20)

        verify(mockTotalCountObserver).onChanged(30)
    }

}