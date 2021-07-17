package com.david0926.sunrinthon2021.view.login

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.david0926.sunrinthon2021.R
import com.david0926.sunrinthon2021.data.auth.LoginRequest
import com.david0926.sunrinthon2021.databinding.ActivityLoginBinding
import com.david0926.sunrinthon2021.network.auth.AuthManager
import com.david0926.sunrinthon2021.util.UserCache
import com.david0926.sunrinthon2021.view.base.MvvmActivity
import com.david0926.sunrinthon2021.view.dialog.LoadingDialog
import com.david0926.sunrinthon2021.view.main.MainActivity
import com.david0926.sunrinthon2021.view.register.RegisterActivity

class LoginActivity : MvvmActivity<ActivityLoginBinding, LoginViewModel>(R.layout.activity_login) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.viewModel = viewModel

        binding.btnLoginLogin.setOnClickListener {
            val dialog = LoadingDialog(this)
            dialog.setMessage("Signing in...").show()

            val authManager = AuthManager(this)
            authManager.login(
                LoginRequest(viewModel.id.value!!, viewModel.pw.value!!),
                onResponse = { _, user, token ->
                    UserCache.setUser(this, user, token)
                    dialog.success("Success!", onSuccess = {
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    })
                },
                onFailure = {
                    dialog.cancel()
                    viewModel.errorMsg.value = it.message
                    it.printStackTrace()
                })
        }

        binding.btnLoginRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}