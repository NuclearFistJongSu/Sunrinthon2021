package com.david0926.sunrinthon2021.data

import java.io.Serializable

data class UserModel(
    var _id: String,
    var token: String,
    var username: String,
    var userId: String,
    var password: String,
    var createdAt: String,
    var isExpert: Boolean,
    var information: String,
    var career: String
):Serializable
{

    companion object {
        var token: String = ""
    }
}