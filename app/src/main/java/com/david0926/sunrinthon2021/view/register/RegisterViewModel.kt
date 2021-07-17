package com.david0926.sunrinthon2021.view.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel(){

    val id = MutableLiveData("")
    val username = MutableLiveData("")
    val pw = MutableLiveData("")
    val term = MutableLiveData(false)

    val btnEnabled = MutableLiveData(false)

    val errorMsg = MutableLiveData("")


}