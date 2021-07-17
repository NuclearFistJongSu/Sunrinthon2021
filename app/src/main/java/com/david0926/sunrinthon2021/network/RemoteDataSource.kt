package com.david0926.sunrinthon2021.network

import com.david0926.sunrinthon2021.data.UserModel
import com.david0926.sunrinthon2021.data.auth.*
import com.david0926.sunrinthon2021.data.post.Post
import com.david0926.sunrinthon2021.data.post.PostRequest
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
        onResponse: (CommonResponse, User?) -> Unit,
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

    fun uploadPost(
        postRequest: PostRequest,
        onResponse: (CommonResponse, Post?) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun getPosts(
        limit: Int?,
        page: Int?,
        onResponse: (CommonResponse, ArrayList<Post>?) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun getPost(
        _id: String,
        onResponse: (CommonResponse, Post?) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun getPostPortfolioImage(
        _id: String,
        onResponse: (CommonResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun addComment(
        _id: String,
        contents: String,
        onResponse: (CommonResponse, Post?) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun voteComment(
        _id: String,
        comment_id: String,
        onResponse: (CommonResponse, Post?) -> Unit,
        onFailure: (Throwable) -> Unit
    )

}