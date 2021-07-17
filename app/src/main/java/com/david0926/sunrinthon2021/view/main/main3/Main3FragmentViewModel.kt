package com.david0926.sunrinthon2021.view.main.main3

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.david0926.sunrinthon2021.data.UserModel
import com.david0926.sunrinthon2021.data.auth.UserInfoRequest
import com.david0926.sunrinthon2021.network.StockerRetrofit
import com.david0926.sunrinthon2021.network.auth.AuthManager


class Main3FragmentViewModel : ViewModel() {

    val counter = MutableLiveData(0)

    fun changeProfile(isExpert: Boolean?, information: String?, career: String?) {
        val authManager = AuthManager()
        authManager.updateUser(UserInfoRequest(isExpert, information, career), {

        }, {

        })

    }


    fun addCounter(){
        counter.value = counter.value!!+1
    }
}