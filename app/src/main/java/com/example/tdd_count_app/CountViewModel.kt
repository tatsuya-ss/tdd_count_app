package com.example.tdd_count_app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountViewModel : ViewModel() {

    private val _totalCount: MutableLiveData<Int> = MutableLiveData()
    val totalCount: LiveData<Int> = _totalCount

    fun onClickPlusButton(firstNumber: Int, secondNumber: Int) {
        _totalCount.value = firstNumber + secondNumber
    }
}