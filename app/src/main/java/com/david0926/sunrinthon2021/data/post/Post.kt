package com.david0926.sunrinthon2021.data.post

import com.david0926.sunrinthon2021.data.UserModel
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

data class Post(
    var _id:String,
    var title:String,
    var contents: String,
    var by: UserModel?,
    var comments: Collection<Comment>,
    var createdAt: String

):Serializable