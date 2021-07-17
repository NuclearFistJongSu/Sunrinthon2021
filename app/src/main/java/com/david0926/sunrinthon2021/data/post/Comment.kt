package com.david0926.sunrinthon2021.data.post

import com.david0926.sunrinthon2021.data.UserModel

data class Comment(
    var _id: String,
    var contents: String,
    var voted_user: ArrayList<Any>,
    var by: UserModel,
    var vote: Int,
    var createdAt: String
)