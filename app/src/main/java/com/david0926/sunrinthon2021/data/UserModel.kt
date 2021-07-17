package com.david0926.sunrinthon2021.data

data class UserModel(
    var _id: String?,
    var token: String?,
    var username: String?,
    var userId: String?,
    var password: String?,
    var createdAt: String?,
    var isExpert: Boolean = false,
    var information: String?,
    var career: String?
) {

    companion object {
        var nowUser: UserModel = UserModel("","","","","","",false,"","")
    }
}