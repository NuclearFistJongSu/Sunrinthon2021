package com.david0926.sunrinthon2021.view.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel(){

    val id = MutableLiveData("")
    val pw = MutableLiveData("")

    val btnEnabled = MutableLiveData(false)

    val errorMsg = MutableLiveData("")


}