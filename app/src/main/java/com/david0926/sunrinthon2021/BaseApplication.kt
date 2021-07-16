package com.david0926.sunrinthon2021

import android.app.Application
import com.sendbird.uikit.SendBirdUIKit
import com.sendbird.uikit.adapter.SendBirdUIKitAdapter
import com.sendbird.uikit.interfaces.UserInfo


class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //SendBird.init("D9128004-9415-48B1-ACA2-B852548EDEEB", applicationContext)
        SendBirdUIKit.init(object : SendBirdUIKitAdapter {
            override fun getAppId(): String {
                return "D9128004-9415-48B1-ACA2-B852548EDEEB" // Specify your Sendbird application ID.
            }

            override fun getAccessToken(): String {
                return ""
            }

            override fun getUserInfo(): UserInfo {
                return object : UserInfo {
                    override fun getUserId(): String {
                        return "roian6" // Specify your user ID.
                    }

                    override fun getNickname(): String {
                        return "정찬효" // Specify your user nickname.
                    }

                    override fun getProfileUrl(): String {
                        return ""
                    }
                }
            }
        }, this)
    }
}