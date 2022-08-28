package com.example.tdd_count_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CountViewModel(
    private val fetchTotalUnreadCountUseCase: FetchTotalUnreadCountUseCase
) : ViewModel() {

    private val _totalCount: MutableLiveData<Int> = MutableLiveData()
    val totalCount: LiveData<Int> = _totalCount

    fun onClickPlusButton(firstNumber: Int, secondNumber: Int) {
        _totalCount.value = firstNumber + secondNumber
    }

    private val _totalUnreadCount: MutableLiveData<Int> = MutableLiveData()
    val totalUnreadCount: LiveData<Int> = _totalUnreadCount

    fun fetchTotalUnreadCount() {
        viewModelScope.launch {
            val unreadCount = fetchTotalUnreadCountUseCase.invoke()
            _totalUnreadCount.value = unreadCount.fromUserCount + unreadCount.fromSystemCount
        }
    }
}