package com.david0926.sunrinthon2021.view.main.main3.profile

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditProfileViewModel : ViewModel() {

    val profile: MutableLiveData<Uri> = MutableLiveData()
    val introduce = MutableLiveData("")

    val errorMsg = MutableLiveData("")
}