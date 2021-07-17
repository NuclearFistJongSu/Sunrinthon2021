package com.david0926.sunrinthon2021.view.main.main3

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.david0926.sunrinthon2021.data.UserModel
import com.david0926.sunrinthon2021.data.auth.UserInfoRequest
import com.david0926.sunrinthon2021.network.StockerRetrofit
import com.david0926.sunrinthon2021.network.auth.AuthManager
import com.david0926.sunrinthon2021.util.UserCache


class Main3FragmentViewModel : ViewModel() {

    fun changeProfile(context: Context, isExpert: Boolean?, information: String?, career: String?) {
        val authManager = AuthManager(context)
        authManager.updateUser(UserInfoRequest(isExpert, information, career), {

        }, {

        })

    }

}