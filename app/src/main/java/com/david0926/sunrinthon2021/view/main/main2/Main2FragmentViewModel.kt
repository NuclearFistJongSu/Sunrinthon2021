package com.david0926.sunrinthon2021.view.main.main2

import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.david0926.sunrinthon2021.data.UserModel
import com.david0926.sunrinthon2021.data.post.Post
import com.david0926.sunrinthon2021.network.auth.AuthManager
import com.david0926.sunrinthon2021.network.post.PostManager
import com.david0926.sunrinthon2021.util.UserCache


class Main2FragmentViewModel : ViewModel() {

    val counter = MutableLiveData(0)

    val PostList = ObservableArrayList<Post>()

    private fun getPersonalPostFromRepo(context:Context, page: Int) {
        val authManager = AuthManager()
        authManager.getUser(UserModel.token, { response, data ->
            if(response.success) {

                if (data != null) {
                    PostList.addAll(data.posts as ObservableArrayList<Post>)
                }
            }
        }, {
            it.printStackTrace()
        })
    }

    fun addCounter(){
        counter.value = counter.value!!+1
    }
}