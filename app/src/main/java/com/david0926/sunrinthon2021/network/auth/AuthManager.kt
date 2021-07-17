package com.david0926.sunrinthon2021.network.auth

import com.david0926.sunrinthon2021.data.UserModel
import com.david0926.sunrinthon2021.data.auth.CommonResponse
import com.david0926.sunrinthon2021.data.auth.LoginRequest
import com.david0926.sunrinthon2021.data.auth.RegisterRequest
import com.david0926.sunrinthon2021.data.auth.UserInfoRequest
import com.david0926.sunrinthon2021.network.RemoteDataSource
import com.david0926.sunrinthon2021.network.RemoteDataSourceImpl

class AuthManager {
    private val retrofitRemoteDataSource: RemoteDataSource = RemoteDataSourceImpl()


    fun login(loginRequest: LoginRequest, onResponse: (CommonResponse, UserModel?) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.login(loginRequest, onResponse, onFailure);
    }

    fun register(registerRequest: RegisterRequest, onResponse: (CommonResponse) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.register(registerRequest, onResponse, onFailure);
    }

    fun getUser(token: String, onResponse: (CommonResponse, UserModel?) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.getuser(token, onResponse, onFailure);
    }

    fun updateUser(userInfoRequest: UserInfoRequest, onResponse: (CommonResponse) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.updateUser(userInfoRequest, onResponse, onFailure)
    }

    fun getProfilePhoto(_id: String, onResponse: (CommonResponse) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.getProfilePhoto(_id, onResponse, onFailure)
    }

}