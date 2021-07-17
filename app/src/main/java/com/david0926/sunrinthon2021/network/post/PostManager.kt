package com.david0926.sunrinthon2021.network.post

import android.content.Context
import com.david0926.sunrinthon2021.data.auth.CommonResponse
import com.david0926.sunrinthon2021.data.post.Post
import com.david0926.sunrinthon2021.data.post.PostRequest
import com.david0926.sunrinthon2021.network.RemoteDataSource
import com.david0926.sunrinthon2021.network.RemoteDataSourceImpl

class PostManager(context: Context) {

    private val retrofitRemoteDataSource: RemoteDataSource = RemoteDataSourceImpl(context)

    fun uploadPost(postRequest: PostRequest, onResponse: (CommonResponse, Post?) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.uploadPost(postRequest, onResponse, onFailure)
    }

    fun getPosts(limit: Int?, page: Int?, onResponse: (CommonResponse, ArrayList<Post>?) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.getPosts(limit, page, onResponse, onFailure)
    }

    fun getPost(_id:String, onResponse: (CommonResponse, Post?) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.getPost(_id, onResponse, onFailure)
    }


    fun getPortfolioImage(post_id:String, onResponse: (CommonResponse) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.getPortfolioImage(post_id, onResponse, onFailure)
    }


    fun addComment(_id:String, contents:String, onResponse: (CommonResponse, Post?) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.addComment(_id, contents, onResponse, onFailure)
    }


    fun voteComment(_id:String, comment_id:String, onResponse: (CommonResponse, Post?) -> Unit, onFailure: (Throwable) -> Unit) {
        retrofitRemoteDataSource.voteComment(_id, comment_id, onResponse, onFailure)
    }
}