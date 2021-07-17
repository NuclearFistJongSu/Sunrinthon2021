package com.david0926.sunrinthon2021.data.post

import com.david0926.sunrinthon2021.data.UserModel
import org.w3c.dom.Comment
import java.io.Serializable

data class Post(
    var _id:String,
    var title:String,
    var contents: String,
    var by: UserModel?,
    var comments: ArrayList<Any>?,
    var createdAt: String

):Serializable