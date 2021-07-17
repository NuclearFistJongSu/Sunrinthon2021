package com.david0926.sunrinthon2021.data.auth

import com.david0926.sunrinthon2021.data.UserModel
import com.david0926.sunrinthon2021.data.post.Post

data class User(
    var user: UserModel,
    var posts: Any
)