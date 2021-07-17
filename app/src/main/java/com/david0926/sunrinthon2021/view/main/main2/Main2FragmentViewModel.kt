package com.david0926.sunrinthon2021.view.main.main2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class Main2FragmentViewModel : ViewModel() {

    val counter = MutableLiveData(0)

    fun addCounter(){
        counter.value = counter.value!!+1
    }
}