package com.david0926.sunrinthon2021.data.auth

data class RegisterRequest(
    var userId: String,
    var password: String,
    var username: String
)