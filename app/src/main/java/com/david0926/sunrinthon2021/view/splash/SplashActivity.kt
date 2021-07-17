package com.david0926.sunrinthon2021.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.david0926.sunrinthon2021.R
import com.david0926.sunrinthon2021.databinding.ActivitySplashBinding
import com.david0926.sunrinthon2021.util.SharedPreferenceUtil
import com.david0926.sunrinthon2021.util.UserCache
import com.david0926.sunrinthon2021.view.base.BaseActivity
import com.david0926.sunrinthon2021.view.login.LoginActivity
import com.david0926.sunrinthon2021.view.main.MainActivity
import com.david0926.sunrinthon2021.view.onboard.OnBoardActivity


class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(mainLooper).postDelayed({

            var state = SharedPreferenceUtil.getString(this, "state", "on_board")

            // TODO: debug - remove this line to show onboard screen once
            state = "on_board"

            when (state) {
                "on_board" ->
                    startActivity(Intent(this, OnBoardActivity::class.java))
                else -> {
                    if (UserCache.getUser(this) == null)
                        startActivity(Intent(this, LoginActivity::class.java))
                    else startActivity(Intent(this, MainActivity::class.java))
                }
            }
            finish()
        }, 2000)

    }
}