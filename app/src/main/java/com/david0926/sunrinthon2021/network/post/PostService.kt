package com.david0926.sunrinthon2021.network.post

import com.david0926.sunrinthon2021.data.auth.CommonResponse
import com.david0926.sunrinthon2021.data.post.CommentRequest
import com.david0926.sunrinthon2021.data.post.PostRequest
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface PostService {

    @POST("/api/v1/post")
    fun uploadPost(
        @Header("Authorization") token: String,
        @Body postRequest: PostRequest
    ): Call<CommonResponse>

    @GET("/api/v1/post")
    fun getPosts(
        @Query("limit") limit: Int?,
        @Query("page") page: Int?
    ): Call<CommonResponse>

    @GET("/api/v1/post/{id}")
    fun getPost(
        @Path("id") id: String
    ): Call<CommonResponse>

    @GET("/api/v1/post/{id}/portfolio_image")
    fun getPortfolio_Image(
        @Path("id") id: String
    ): Call<CommonResponse>

    @POST("/api/v1/post/{id}/comment")
    fun addComment(
        @Path("id") id: String,
        @Header("Authorization") token: String,
        @Body commentRequest: CommentRequest?
    ):Call<CommonResponse>

    @POST("/api/v1/post/{id}/comment/{comment_id}/vote")
    fun voteComment(
        @Path("id") id: String,
        @Path("comment_id") comment_id: String,
        @Header("Authorization") token: String
    ):Call<CommonResponse>

}