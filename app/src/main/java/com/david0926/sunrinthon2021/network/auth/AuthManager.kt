package com.david0926.sunrinthon2021.network.auth

import com.david0926.sunrinthon2021.data.UserModel
import com.david0926.sunrinthon2021.data.auth.*
import com.david0926.sunrinthon2021.network.RemoteDataSource
import com.david0926.sunrinthon2021.network.RemoteDataSourceImpl
import okhttp3.MultipartBody

class AuthManager {
    private val retrofitRemoteDataSource: RemoteDataSource = RemoteDataSourceImpl()


    fun login(loginRequest: LoginRequest, onResponse: (CommonResponse, UserModel?) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.login(loginRequest, onResponse, onFailure);
    }

    fun register(registerRequest: RegisterRequest, onResponse: (CommonResponse) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.register(registerRequest, onResponse, onFailure);
    }

    fun getUser(token: String, onResponse: (CommonResponse, User?) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.getuser(token, onResponse, onFailure);
    }

    fun updateUser(userInfoRequest: UserInfoRequest, onResponse: (CommonResponse) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.updateUser(userInfoRequest, onResponse, onFailure)
    }

    fun getProfilePhoto(_id: String, onResponse: (CommonResponse) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.getProfilePhoto(_id, onResponse, onFailure)
    }
    fun updateProfilePhoto(image: MultipartBody.Part, onResponse: (CommonResponse) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.updateProfilePhoto(image, onResponse, onFailure)
    }

    fun getPortfolioImage(_id: String, onResponse: (CommonResponse) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.getPortfolioImage(_id, onResponse, onFailure)
    }
    fun updatePortfolioImage(image: MultipartBody.Part, onResponse: (CommonResponse) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.updatePortfolioImage(image, onResponse, onFailure)
    }
}