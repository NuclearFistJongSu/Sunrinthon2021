package com.david0926.sunrinthon2021.view.dialog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoadingDialogViewModel : ViewModel() {
    val msg = MutableLiveData("")
    val isSuccess = MutableLiveData(false)
}