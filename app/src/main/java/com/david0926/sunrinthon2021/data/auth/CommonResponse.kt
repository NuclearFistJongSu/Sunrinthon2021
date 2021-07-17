package com.david0926.sunrinthon2021.data.auth

data class CommonResponse(
    var success: Boolean,
    var code: String,
    var message: String,
    var data: Any
)