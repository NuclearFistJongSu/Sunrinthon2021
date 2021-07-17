package com.david0926.sunrinthon2021.view.register

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.david0926.sunrinthon2021.R
import com.david0926.sunrinthon2021.data.auth.RegisterRequest
import com.david0926.sunrinthon2021.databinding.ActivityRegisterBinding
import com.david0926.sunrinthon2021.network.auth.AuthManager
import com.david0926.sunrinthon2021.view.base.MvvmActivity
import com.david0926.sunrinthon2021.view.dialog.LoadingDialog

class RegisterActivity :
    MvvmActivity<ActivityRegisterBinding, RegisterViewModel>(R.layout.activity_register) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.viewModel = viewModel

        binding.btnRegisterRegister.setOnClickListener {
            val dialog = LoadingDialog(this)
            dialog.setMessage("Creating account...").show()

            val authManager = AuthManager(this)
            authManager.register(
                RegisterRequest(
                    viewModel.id.value!!,
                    viewModel.pw.value!!,
                    viewModel.username.value!!
                ),
                onResponse = {
                    dialog.success("Success!", onSuccess = {
                        finish()
                    })
                },
                onFailure = {
                    dialog.cancel()
                    viewModel.errorMsg.value = it.message
                    it.printStackTrace()
                })
        }

        binding.toolbarRegister.setNavigationOnClickListener {
            finish()
        }
    }
}