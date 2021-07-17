package com.david0926.sunrinthon2021.data.auth

import com.david0926.sunrinthon2021.data.UserModel

data class User(
    var user: UserModel,
    var posts: ArrayList<Any>
)