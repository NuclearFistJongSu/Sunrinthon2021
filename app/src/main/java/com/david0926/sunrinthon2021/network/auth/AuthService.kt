package com.david0926.sunrinthon2021.network.auth

import com.david0926.sunrinthon2021.data.auth.CommonResponse
import com.david0926.sunrinthon2021.data.auth.LoginRequest
import com.david0926.sunrinthon2021.data.auth.RegisterRequest
import com.david0926.sunrinthon2021.data.auth.UserInfoRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface AuthService {

    @POST("/api/v1/user/issue")
    fun login(
        @Body loginRequest: LoginRequest
    ): Call<CommonResponse>

    @GET("/api/v1/user")
    fun getUser(
        @Header("Authorization") Authorization: String
    ): Call<CommonResponse>

    @POST("/api/v1/user")
    fun register(
        @Body registerRequest: RegisterRequest
    ): Call<CommonResponse>

    @PUT("/api/v1/user")
    @Multipart
    fun updateUser(
        @Header("Authorization") token:String,
        @Part("isExpert") isexpert: RequestBody?,
        @Part("information") information: RequestBody?,
        @Part("career") career: RequestBody?
    ): Call<CommonResponse>

    @GET("/api/v1/user/{id}/profile_image")
    fun getProfilePhoto(
        @Path("id") id: String
    ): Call<CommonResponse>

    @PUT("/api/v1/user/profile_image")
    @Multipart
    fun updateProfilePhoto(
        @Header("Authorization") token:String,
        @Part("image") image: MultipartBody.Part
    ):Call<CommonResponse>

    @GET("/api/v1/user/{id}/portfolio_image")
    fun getPortfolioImage(
        @Path("id") id: String
    ): Call<CommonResponse>

    @PUT("/api/v1/user/portfolio_image")
    @Multipart
    fun updatePortfolioImage(
        @Header("Authorization") token:String,
        @Part("image") image: MultipartBody.Part
    ):Call<CommonResponse>
}