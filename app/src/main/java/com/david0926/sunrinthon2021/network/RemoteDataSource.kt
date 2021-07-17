package com.david0926.sunrinthon2021.network

import com.david0926.sunrinthon2021.data.UserModel
import com.david0926.sunrinthon2021.data.auth.CommonResponse
import com.david0926.sunrinthon2021.data.auth.LoginRequest
import com.david0926.sunrinthon2021.data.auth.RegisterRequest
import com.david0926.sunrinthon2021.data.auth.UserInfoRequest
import okhttp3.MultipartBody
import retrofit2.http.Multipart

interface RemoteDataSource {

    fun register(
        registerRequest: RegisterRequest,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun login(
        loginRequest: LoginRequest,
        onResponse: (CommonResponse, UserModel?) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun getuser(
        Authorization: String,
        onResponse: (CommonResponse, UserModel?) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun updateUser(
        userInfoRequest: UserInfoRequest,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun getProfilePhoto(
        _id: String,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun updateProfilePhoto(
        image: MultipartBody.Part,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun getPortfolioImage(
        _id: String,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun updatePortfolioImage(
        image: MultipartBody.Part,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    )
}