package com.david0926.sunrinthon2021.view.main.main4

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class Main4FragmentViewModel : ViewModel() {

    val counter = MutableLiveData(0)

    fun addCounter(){
        counter.value = counter.value!!+1
    }
}